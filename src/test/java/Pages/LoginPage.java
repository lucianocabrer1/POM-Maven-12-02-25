package Pages;

import DriverManager.Utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

public class LoginPage {
    @FindBy(id="loginusername")
    WebElement user;
    @FindBy(id="loginpassword")
    WebElement pwd;
    @FindBy(xpath="//button[text()=\"Log in\"]")
    WebElement login;

    WebDriver driver;
    WebDriverWait wait;
    ExtentTest test;

    Utils utils;
    public LoginPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage(WebDriver driver, WebDriverWait wait, ExtentTest test){
        this.driver = driver;
        this.wait = wait;
        this.test = test;

        PageFactory.initElements(this.driver, this);
    }
    public LoginPage(WebDriver driver, WebDriverWait wait, ExtentTest test, Utils utils){
        this.utils = utils;
        this.driver = driver;
        this.wait = wait;
        this.test = test;

        PageFactory.initElements(this.driver, this);
    }

    public void login(Map<String, String> data) throws IOException, InvalidFormatException {
        if(data.get("flag").equals("true")) {
            wait.until(ExpectedConditions.elementToBeClickable(user)).sendKeys(data.get("user"));
            wait.until(ExpectedConditions.elementToBeClickable(pwd)).sendKeys(data.get("password"));
            wait.until(ExpectedConditions.elementToBeClickable(login)).click();
            String user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))).getText().split(" ")[1];
            Assert.assertEquals(data.get("user"), user);
            test.log(Status.INFO, "Se realiza el login correcto con el usuario " + data.get("user") + "  y el password " + data.get("password"));
        }
        else {
            wait.until(ExpectedConditions.elementToBeClickable(user)).sendKeys(data.get("user"));
            wait.until(ExpectedConditions.elementToBeClickable(pwd)).sendKeys(data.get("password"));
            wait.until(ExpectedConditions.elementToBeClickable(login)).click();
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            test.log(Status.INFO, "Se realiza el login incorrecto con el usuario " + data.get("user") + "  y el password " + data.get("password"));
        }
    }

    public void login(Map<String, String> data, Map<String, String> evidencia) throws IOException, InvalidFormatException {
        if(data.get("flag").equals("true")) {
            wait.until(ExpectedConditions.elementToBeClickable(user)).sendKeys(data.get("user"));
            wait.until(ExpectedConditions.elementToBeClickable(pwd)).sendKeys(data.get("password"));
            wait.until(ExpectedConditions.elementToBeClickable(login)).click();
            String user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))).getText().split(" ")[1];
            Assert.assertEquals(data.get("user"), user);
            test.log(Status.INFO, "Se realiza el login correcto con el usuario " + data.get("user") + "  y el password " + data.get("password"));
            //utils.TakeScreenshot("LoginOK");
            //test.addScreenCaptureFromPath(utils.TakeScreenshot("login-Correcto"));
            utils.createFileWithEvidenceScreenShot(evidencia);
        }
        else {
            wait.until(ExpectedConditions.elementToBeClickable(user)).sendKeys(data.get("user"));
            wait.until(ExpectedConditions.elementToBeClickable(pwd)).sendKeys(data.get("password"));
            wait.until(ExpectedConditions.elementToBeClickable(login)).click();
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            test.log(Status.INFO, "Se realiza el login incorrecto con el usuario " + data.get("user") + "  y el password " + data.get("password"));
        }
    }
}
