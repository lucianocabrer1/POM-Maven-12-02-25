package Test;

import DriverManager.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFailed extends BaseTest {

    @Test
    public void loginTestOK() throws IOException, InvalidFormatException {
        HomePage home = new HomePage(getDriver(), getWait());
        home.goToLogin();

        Map<String, String> user = new HashMap<>();
        user.put("user", "123");
        user.put("password", "asddasdasasd");
        user.put("flag", "false");

        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login(user);
    }
}
