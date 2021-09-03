import commonLib.Browser;
import input.InputNewCustomer;
import input.InputLoginCred;
import input.InputNewAccount;
import locator.LocatorNewAccount;
import locator.LocatorNewCustomer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.Login;

public class AddNewCustomerAndAccountTest {
    Browser browser = new Browser();
    Login login = new Login();
    InputLoginCred inputLoginCred = new InputLoginCred();
    LocatorNewCustomer locatorNewCustomer = new LocatorNewCustomer();
    LocatorNewAccount locatorNewAccount = new LocatorNewAccount();
    InputNewCustomer inputNewCustomer = new InputNewCustomer();
    InputNewAccount inputNewAccount = new InputNewAccount();


    @BeforeClass
    public void setBrowser(){
        browser.openBrowser("Chrome");
        login.login(browser,inputLoginCred.validUserId,inputLoginCred.validPassword);
    }

    @AfterClass
    public void tearDown(){
        browser.driver.quit();
    }

    @Test()
    public void verifyAddCustomer() {
        browser.driver.findElement(By.xpath(locatorNewCustomer.navLinkNewCustomer)).click();
//      fill add new customer form
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputCustomerName)).sendKeys(inputNewCustomer.customerName);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputRadioGenderMale)).click();
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputDateOfBirth)).sendKeys(inputNewCustomer.dateOfBirth);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputAddress)).sendKeys(inputNewCustomer.address);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputCity)).sendKeys(inputNewCustomer.city);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputState)).sendKeys(inputNewCustomer.state);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputPin)).sendKeys(inputNewCustomer.pin);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputMobileNumber)).sendKeys(inputNewCustomer.mobileNumber);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputEmail)).sendKeys(inputNewCustomer.email);
        browser.driver.findElement(By.xpath(locatorNewCustomer.inputPassword)).sendKeys(inputNewCustomer.password);
        browser.driver.findElement(By.xpath(locatorNewCustomer.buttonSubmit)).click();
        String expectedText = "Customer Registered Successfully!!!";
        String actualResult = browser.driver.findElement(By.xpath(locatorNewCustomer.messageSuccessCustomerReg)).getText();
        Assert.assertEquals(actualResult, expectedText);

        InputNewCustomer.customerId = browser.driver.findElement(By.xpath(locatorNewCustomer.customerId)).getText();
    }

    @Test(priority = 1)
    public void verifyAddNewAccountToNewCustomer(){

        browser.driver.findElement(By.xpath(locatorNewAccount.navLinkNewAccount)).click();

        browser.driver.findElement(By.xpath(locatorNewAccount.inputCustomerId)).sendKeys(InputNewCustomer.customerId);
        Select accountType = new Select(browser.driver.findElement(By.xpath(locatorNewAccount.selectAccountType)));
        accountType.selectByValue(inputNewAccount.accountType);
        browser.driver.findElement(By.xpath(locatorNewAccount.initialDeposit)).sendKeys(inputNewAccount.initialDeposit);
        browser.driver.findElement(By.xpath(locatorNewAccount.buttonSubmit)).click();
        Assert.assertTrue(browser.driver.findElement(By.xpath(locatorNewAccount.messageSuccessAccountReg)).isDisplayed());

        InputNewAccount.accountId = browser.driver.findElement(By.xpath(locatorNewAccount.accountId)).getText();
    }

}
