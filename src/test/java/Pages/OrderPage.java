package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Map;

public class OrderPage {
    @FindBy(id = "name")
    WebElement name;
    @FindBy(id = "country")
    WebElement country;
    @FindBy(id = "city")
    WebElement city;
    @FindBy(id = "card")
    WebElement card;
    @FindBy(id = "month")
    WebElement month;
    @FindBy(id = "year")
    WebElement year;
    @FindBy(xpath = "//button[text() = 'Purchase']")
    WebElement purchaseButton;
    WebDriver driver;
    WebDriverWait wait;

    public OrderPage(WebDriver driver, WebDriverWait wait){
        this.driver =  driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }
    public void fillData(Map<String, String> data) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(name)).sendKeys(data.get("name"));
        wait.until(ExpectedConditions.visibilityOf(country)).sendKeys(data.get("country"));
        wait.until(ExpectedConditions.visibilityOf(city)).sendKeys(data.get("city"));
        wait.until(ExpectedConditions.visibilityOf(card)).sendKeys(data.get("card"));
        wait.until(ExpectedConditions.visibilityOf(month)).sendKeys(data.get("month"));
        wait.until(ExpectedConditions.visibilityOf(year)).sendKeys(data.get("year"));
        wait.until(ExpectedConditions.visibilityOf(purchaseButton)).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text() = \"Thank you for your purchase!\"]"))).isDisplayed());
    }
}

//html/body/div[6]/div/div[1]/div/table/tbody/tr[1]/td[2]