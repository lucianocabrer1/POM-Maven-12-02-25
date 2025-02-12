package DriverManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest extends ExecutionContext{
    @BeforeTest
    public void setUp(){
        THREAD_LOCAL_SPARK_REPORTER = new ExtentSparkReporter(System.getProperty("user.dir") +
                "\\src\\test\\resources\\reportes\\testReport"+ DATE + ".html");

        THREAD_LOCAL_EXTENT_REPORTS = new ExtentReports();

        THREAD_LOCAL_EXTENT_REPORTS.attachReporter(THREAD_LOCAL_SPARK_REPORTER);

        THREAD_LOCAL_SPARK_REPORTER.config().setOfflineMode(true);
        THREAD_LOCAL_SPARK_REPORTER.config().setDocumentTitle("Regresion Educacion IT Luciano9 18-01-25");
        THREAD_LOCAL_SPARK_REPORTER.config().setReportName("Test Educacion IT");
        THREAD_LOCAL_SPARK_REPORTER.config().setTheme(Theme.DARK);
        THREAD_LOCAL_SPARK_REPORTER.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        THREAD_LOCAL_SPARK_REPORTER.config().setEncoding("UTF-8");

        WebDriver driver = DriverManager.SetUp("chrome");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        DRIVER.set(driver);
        WAIT.set(wait);

        DRIVER.get().get("https://www.demoblaze.com/");
    }

    public static WebDriver getDriver(){
        return DRIVER.get();
    }

    public static WebDriverWait getWait(){
        return WAIT.get();
    }

    @AfterTest
    public void tearDown(){
        if(getDriver() != null)
            getDriver().quit();
        THREAD_LOCAL_EXTENT_REPORTS.flush();
    }

    public static ExtentTest getExtentTest(){
        return THREAD_LOCAL_EXTENT_TEST;
    }
}
