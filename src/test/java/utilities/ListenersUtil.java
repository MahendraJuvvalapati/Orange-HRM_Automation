package utilities;

import base.BaseTest; // ✅ Import your BaseTest to get thread-safe driver
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenersUtil implements ITestListener {

    private static final Logger log = LogManager.getLogger(ListenersUtil.class);
    private static final ExtentReports extent = ExtentReportUtils.createInstance();
    private static final Map<String, ExtentTest> classNodeMap = new HashMap<>();

    @Override
    public void onStart(ITestContext context) {
        log.info("▶️ Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("🏁 Suite finished: " + context.getName());
        ExtentReportUtils.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();
        ExtentTest classNode = classNodeMap.computeIfAbsent(className,
                name -> extent.createTest(name));

        ExtentTest methodNode = classNode.createNode(result.getMethod().getMethodName());
        ExtentReportUtils.setTest(methodNode);

        result.setAttribute("startTime", LocalDateTime.now());
        log.info("🔹 Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LocalDateTime start = (LocalDateTime) result.getAttribute("startTime");
        long duration = Duration.between(start, LocalDateTime.now()).toMillis();

        log.info("✅ Test passed: " + result.getName() + " (" + duration + " ms)");
        ExtentReportUtils.getTest().log(Status.PASS, "Test passed in " + duration + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LocalDateTime start = (LocalDateTime) result.getAttribute("startTime");
        long duration = Duration.between(start, LocalDateTime.now()).toMillis();

        log.error("❌ Test failed: " + result.getName() + " (" + duration + " ms)");
        ExtentReportUtils.getTest().log(Status.FAIL, result.getThrowable());
        ExtentReportUtils.getTest().log(Status.FAIL, "Failed in " + duration + " ms");

        WebDriver driver = BaseTest.getDriver(); // ✅ Use thread-safe driver
        if (driver != null) {
            try {
                String screenshotPath = takeScreenshot(driver, result.getName());
                if (screenshotPath != null) {
                    ExtentReportUtils.getTest().addScreenCaptureFromPath(screenshotPath);
                    log.info("📸 Screenshot attached: " + screenshotPath);
                }
            } catch (Exception e) {
                log.error("Failed to capture screenshot", e);
            }
        } else {
            log.warn("⚠️ WebDriver instance is null, screenshot not taken");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("⚠️ Test skipped: " + result.getName());
        ExtentReportUtils.getTest().log(Status.SKIP, "Test skipped");
    }

    private String takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String dir = System.getProperty("user.dir") + "/screenshots";
            new File(dir).mkdirs();

            String path = dir + "/" + testName + "_" + timestamp + ".png";
            FileHandler.copy(src, new File(path));

            log.info("📸 Screenshot saved: " + path);
            return path;

        } catch (IOException e) {
            log.error("Failed to save screenshot", e);
            return null;
        }
    }
}
