package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    @FindBy(className = "price-container")
    WebElement priceText;
    @FindBy(xpath = "//a[text() = \"Add to cart\"]")
    WebElement addToCartButton;
    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver, WebDriverWait wait){
        this.driver =  driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }
    public void addToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
    public String selectPrice(){
        return wait.until(ExpectedConditions.visibilityOf(priceText)).getText().split(" ")[0].split("\\$")[1];
    }
}

//html/body/div[6]/div/div[1]/div/table/tbody/tr[1]/td[2]