package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage{
    @FindBy(id = "login2")
    WebElement loginButton;
    @FindBy(id = "cartur")
    WebElement cartButton;
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver =  driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }
    public void goToCart(){
        wait.until(ExpectedConditions.visibilityOf(cartButton)).click();
    }
    public void goToLogin(){
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
    }
    public void selectCategory(String category){
        String aux = "//*[@id= 'itemc' and text() = '"+ category +"']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(aux))).click();
    }
    public void selectDevice(String device){
        String aux = "//a[text() = '"+ device +"']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(aux))).click();
        wait.until(ExpectedConditions.urlContains("/prod.html"));
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name"))).getText(),
                device);
    }
}
