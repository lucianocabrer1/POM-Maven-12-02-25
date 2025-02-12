package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CartPage {
    @FindBy(xpath = "//button[text() = \"Place Order\"]")
    WebElement placeOrder;
    @FindBy(id = "tbodyid")
    WebElement table;
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait){
        this.driver =  driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }
    public int getRows() throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        return rows.size();
    }

    public int getCol()throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> col = table.findElements(By.tagName("td"));
        int rowCount = getRows();

        return (col.size() / rowCount);
    }

    public int checkDeviceExist(String device, String price, int size){
        int exist = 0;

        for(int i = 1; i <= size; i++){
            String aux1 = "//tbody[@id=\"tbodyid\"]/tr[" + i + "]/td[2]",
                    aux2 = "//tbody[@id=\"tbodyid\"]/tr[" + i + "]/td[3]";

            if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(aux1))).getText().equals(device)){
                Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(aux2))).getText(), price);
                exist = 1;
                break;
            }
        }
        return exist;
    }

    public int checkMultipleDevices(String device, int size){
        int count = 0;

        for(int i = 1; i <= size; i++){
            String cadPart1 = "//tbody[@id=\"tbodyid\"]/tr[",
                    cadPart2 = "]/td[2]",
                    cadAux2 = "]/td[3]";

            if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cadPart1 + i + cadPart2))).getText().equals(device)){
                count++;
            }
        }
        return count;
    }
    public void goToPlaceOrder(){
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder)).click();
    }
}

//html/body/div[6]/div/div[1]/div/table/tbody/tr[1]/td[2]