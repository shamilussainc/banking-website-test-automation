import commonLib.Browser;
import input.InputLoginCred;
import input.InputNewAccount;
import input.InputNewCustomer;
import locator.LocatorDeleteCustomer;
import locator.LocatorEditCustomer;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.WebAppMethods;

public class DeleteCustomerTest {
    Browser browser = new Browser();
    WebAppMethods webAppMethods = new WebAppMethods();

    LocatorDeleteCustomer locatorDeleteCustomer = new LocatorDeleteCustomer();
    LocatorEditCustomer locatorEditCustomer = new LocatorEditCustomer();

    InputLoginCred inputLoginCred = new InputLoginCred();
    InputNewAccount account = new InputNewAccount();
    InputNewCustomer customer = new InputNewCustomer();



    @BeforeClass()
    public void setBrowser(){
        browser.openBrowser("Chrome");
        webAppMethods.login(browser,inputLoginCred.validUserId,inputLoginCred.validPassword);
        customer.customerId = webAppMethods.createCustomer(browser);

    }

    @AfterClass
    public void tearDown(){
        browser.driver.close();
    }


//    Verify confirmation message is shown when customer is deleted
    @Test()
    public void verifyConfirmationMessage(){
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.navLinkDeleteCustomer)).click();
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.inputCustomerId)).sendKeys(customer.customerId);
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.buttonSubmit)).click();
        String expectedAlertText = "Do you really want to delete this Customer?";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().dismiss();

    }

//    Verify that customer should not be deleted if any account exists for that customer
    @Test(priority = 1)
    public void verifyDeletionOfCustomerHavingAccount() {
        account.accountId = webAppMethods.createAccount(browser,customer.customerId);
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.navLinkDeleteCustomer)).click();
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.inputCustomerId)).sendKeys(customer.customerId);
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.buttonSubmit)).click();
        String expectedAlertText = "Do you really want to delete this Customer?";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().accept();
        String expectedAlert = "Customer could not be deleted!!. First delete all accounts of this customer then delete the customer";
        Alert alert = browser.driver.switchTo().alert();
        String actualAlert = alert.getText();
        Assert.assertEquals(actualAlert,expectedAlert);
        alert.accept();
    }

//    Verify that a Customer can be Deleted
    @Test(priority = 2)
    public void verifyDeleteCustomer(){
        webAppMethods.deleteAccount(browser, account.accountId);
        webAppMethods.deleteCustomer(browser, customer.customerId);
    }

//    Verify deleted customer cannot be edited
    @Test(priority = 3)
    public void verifyEditDeletedCustomer(){
        browser.driver.findElement(By.xpath(locatorEditCustomer.navLinkEditCustomer)).click();
        browser.driver.findElement(By.xpath(locatorEditCustomer.inputCustomerId)).sendKeys(customer.customerId);
        browser.driver.findElement(By.xpath(locatorEditCustomer.buttonSubmit)).click();
        String expectedAlertText = "Customer does not exist!!";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().accept();
    }

//    Verify system behaviour when manager deletes a non existing customer ID
    @Test(priority=4)
    public void verifyDeleteNonExistingCustomer(){
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.navLinkDeleteCustomer)).click();
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.inputCustomerId)).sendKeys(customer.customerId);
        browser.driver.findElement(By.xpath(locatorDeleteCustomer.buttonSubmit)).click();
        browser.driver.switchTo().alert().accept();
        String expectedAlertText = "Customer does not exist!!";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().accept();
    }
}
