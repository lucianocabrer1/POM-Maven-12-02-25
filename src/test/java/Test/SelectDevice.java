package Test;

import DriverManager.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SelectDevice extends BaseTest {

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
        Thread.sleep(3000);
    }
}
