package Test;

import DriverManager.BaseTest;
import Pages.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class fillPurchase extends BaseTest {

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

        Map<String, String> data = new HashMap<>();
        data.put("name", "Luciano Cabrera");
        data.put("country", "Argentina");
        data.put("city", "Bs.As");
        data.put("card", "023123124564956645");
        data.put("month", "Enero");
        data.put("year", "2025");

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
            OrderPage orderPage = new OrderPage(getDriver(),getWait());
            orderPage.fillData(data);
        }


    }
}
