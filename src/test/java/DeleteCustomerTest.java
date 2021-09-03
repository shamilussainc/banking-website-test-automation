import commonLib.Browser;
import input.InputLoginCred;
import input.InputNewCustomer;
import locator.LocatorDeleteCustomer;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.Login;

public class DeleteCustomerTest {

    Browser browser = new Browser();
    Login login = new Login();

    LocatorDeleteCustomer locatorDeleteCustomer = new LocatorDeleteCustomer();

    InputLoginCred inputLoginCred = new InputLoginCred();



    @BeforeClass()
    public void setBrowser(){
        browser.openBrowser("Chrome");
        login.login(browser,inputLoginCred.validUserId,inputLoginCred.validPassword);
    }

    @AfterClass
    public void tearDown(){
        browser.driver.close();
    }


    @Test(priority=3)
    public void verifyDeleteCustomer(){
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.navLinkDeleteCustomer)).click();
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.inputCustomerId)).sendKeys(InputNewCustomer.customerId);
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.buttonSubmit)).click();
        browser.driver.switchTo().alert().accept();
        Alert alert = browser.driver.switchTo().alert();
        String expectedAlertText = "Customer deleted Successfully";
        String actualAlertText= alert.getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        alert.accept();
    }

}
