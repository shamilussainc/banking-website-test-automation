import commonLib.Browser;
import input.InputLoginCred;
import locator.LocatorChangePassword;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import url.Url;
import utility.Login;

public class ChangePasswordAndLoginTest {
    Browser browser = new Browser();
    Login login = new Login();

    @BeforeClass
    public void goToTestScop(){
//        browser.openBrowser("Chrome");
        browser.openBrowser("Mozilla");
        browser.navigate(Url.baseUrl);
        login.loginAndGoToHomePage(browser.driver);
    }

    @Test(priority = 0)
    public void enterIncorrectOldPassword(){
        browser.driver.findElement(By.xpath(LocatorChangePassword.buttonChangePassword)).click();
//      enter incorrect old password
        browser.driver.findElement(By.xpath(LocatorChangePassword.inputOldPassword)).sendKeys(InputLoginCred.invalidPassword);
        browser.driver.findElement(By.xpath(LocatorChangePassword.inputNewPassword)).sendKeys("123456");
        browser.driver.findElement(By.xpath(LocatorChangePassword.inputConfirmPassword)).sendKeys("123456");
        browser.driver.findElement(By.xpath(LocatorChangePassword.buttonSubmit)).click();
    }

}
