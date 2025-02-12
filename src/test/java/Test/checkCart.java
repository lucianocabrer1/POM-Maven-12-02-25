package Test;

import DriverManager.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class checkCart extends BaseTest {

    @Test
    public void loginTestOK() throws InterruptedException, IOException, InvalidFormatException {
        HomePage home = new HomePage(getDriver(), getWait());
        home.goToLogin();

        Map<String, String> user = new HashMap<>();
        user.put("user", "asd");
        user.put("password", "asd");
        user.put("flag", "true");
        user.put("category", "Laptops");
        user.put("device", "Sony vaio i5");

        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login(user);

        home.selectCategory(user.get("category"));

        home.selectDevice(user.get("device"));

        ProductPage productPage = new ProductPage(getDriver(),getWait());
        user.put("price", productPage.selectPrice());
        productPage.addToCart();

        CartPage cartPage = new CartPage(getDriver(),getWait());

        home.goToCart();

        int size = cartPage.getRows();

        if( cartPage.checkDeviceExist(user.get("device"), user.get("price"), size) == 1){
            cartPage.goToPlaceOrder();
            Thread.sleep(3000);
        }


    }
}
