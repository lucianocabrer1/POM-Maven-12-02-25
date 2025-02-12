package Test;

import DriverManager.BaseTest;
import DriverManager.TestListener;
import DriverManager.Utils;
import Pages.HomePage;
import Pages.LoginPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Listeners(TestListener.class)
public class LoginOK extends BaseTest {

    @Test
    public void loginTestOK() throws IOException, InvalidFormatException {
        THREAD_LOCAL_EXTENT_TEST = THREAD_LOCAL_EXTENT_REPORTS.createTest("Login Correcto",
                "Se desea probar el login");

        HomePage home = new HomePage(getDriver(), getWait());
        home.goToLogin();

        Map<String, String> user = new HashMap<>();
        user.put("user", "asd");
        user.put("password", "asd");
        user.put("flag", "true");

        Map<String, String> evidencia = new HashMap<>();
        evidencia.put("nameScreenshot", "Login_OK");
        evidencia.put("nameDocx", "EvidenciasLoginPassed");
        evidencia.put("titulo", "ED IT Archivo Evidencias Luciano Cabrera");

        Utils utils = new Utils(getDriver());
        LoginPage loginPage = new LoginPage(getDriver(), getWait(), getExtentTest(), utils);
        loginPage.login(user, evidencia);
    }
}
