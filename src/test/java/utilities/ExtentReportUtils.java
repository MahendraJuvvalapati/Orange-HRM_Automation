package utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtils {

    private static final Logger log = LogManager.getLogger(ExtentReportUtils.class); // Logger instance
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize and return ExtentReports instance
    public static ExtentReports createInstance() {
        if (extent == null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String reportDir = System.getProperty("user.dir") + "/reports";
            new File(reportDir).mkdirs();
            String reportPath = reportDir + "/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(spark);

            log.info("✅ ExtentReports initialized at: " + reportPath);
        }
        return extent;
    }

    // Thread-safe ExtentTest getter/setter
    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
        log.info("🔹 ExtentTest created: " + extentTest.getModel().getName());
    }

    public static void removeTest() {
        ExtentTest removedTest = test.get();
        test.remove();
        if (removedTest != null) {
            log.info("🗑 Removed ExtentTest: " + removedTest.getModel().getName());
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            log.info("🏁 ExtentReports flushed successfully");
        }
    }
}
