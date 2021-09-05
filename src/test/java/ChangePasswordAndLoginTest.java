import commonLib.Browser;
import input.InputLoginCred;
import locator.LocatorChangePassword;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.WebAppMethods;


/**
 * test cases in this class are dependent.
 * so execute only in the specified order.
 * also check current password in the "inputLoginCred.validPassword" is valid.
 * executing test case "verifyChangePasswordAndRedirection" will change current password.
 */
public class ChangePasswordAndLoginTest {
    Browser browser = new Browser();
    LocatorChangePassword locatorChangePassword = new LocatorChangePassword();
    InputLoginCred inputLoginCred = new InputLoginCred();
    WebAppMethods webAppMethods = new WebAppMethods();

    @BeforeClass
    public void goToTestScop(){
        browser.openBrowser("Chrome");
//        browser.openBrowser("Mozilla");
        webAppMethods.login(browser,inputLoginCred.validUserId,inputLoginCred.validPassword);
    }
    @AfterClass
    public void tearDown(){
        browser.driver.close();
    }

    @Test()
    public void enterIncorrectOldPassword(){
        browser.driver.findElement(By.xpath(locatorChangePassword.navLinkChangePassword)).click();
//      enter incorrect old password
        browser.driver.findElement(By.xpath(locatorChangePassword.inputOldPassword)).sendKeys(inputLoginCred.invalidPassword);
        browser.driver.findElement(By.xpath(locatorChangePassword.inputNewPassword)).sendKeys(inputLoginCred.validPasswordNew);
        browser.driver.findElement(By.xpath(locatorChangePassword.inputConfirmPassword)).sendKeys(inputLoginCred.validPasswordNew);
        browser.driver.findElement(By.xpath(locatorChangePassword.buttonSubmit)).click();

        //verify the alert message is correct
        Alert alert = browser.driver.switchTo().alert();
        String actualAlertText = alert.getText();
        String expectedAlertText = "Old Password is incorrect";
        Assert.assertEquals(actualAlertText, expectedAlertText);
        alert.accept();

        //verify the redirection is correct
        String actualUrl = browser.driver.getCurrentUrl();
        String expectedUrl = "http://www.demo.guru99.com/V4/manager/PasswordInput.php";
        Assert.assertEquals(actualUrl,expectedUrl);

    }

    @Test(priority=1)
    public void verifyChangePasswordAndRedirection(){
        browser.driver.findElement(By.xpath(locatorChangePassword.navLinkChangePassword)).click();
        browser.driver.findElement(By.xpath(locatorChangePassword.inputOldPassword)).sendKeys(inputLoginCred.validPassword);
        browser.driver.findElement(By.xpath(locatorChangePassword.inputNewPassword)).sendKeys(inputLoginCred.validPasswordNew);
        browser.driver.findElement(By.xpath(locatorChangePassword.inputConfirmPassword)).sendKeys(inputLoginCred.validPasswordNew);
        browser.driver.findElement(By.xpath(locatorChangePassword.buttonSubmit)).click();

//        verify the alert message is correct
        Alert alert = browser.driver.switchTo().alert();
        String actualAlertText = alert.getText();
        String expectedAlertText = "Password is Changed";
        Assert.assertEquals(actualAlertText,expectedAlertText);
        alert.accept();

//        verify URL redirection
        String actualUrl = browser.driver.getCurrentUrl();
        String expectedUrl = "http://www.demo.guru99.com/V4/index.php";
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(priority = 2)
    public void verifyLoginWithNewPassword(){
        webAppMethods.login(browser,inputLoginCred.validUserId,inputLoginCred.validPasswordNew);

        //verify that user redirected to manager home page
        String expectedUrl = "http://www.demo.guru99.com/V4/manager/Managerhomepage.php";
        String actualUrl = browser.driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }

}
