import commonLib.Browser;
import input.InputLoginCred;
import input.InputNewAccount;
import input.InputNewCustomer;
import locator.LocatorCustomisedStatement;
import locator.LocatorFundTransfer;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.WebAppMethods;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FundTransfer {
    Browser browser = new Browser();
    WebAppMethods webAppMethods = new WebAppMethods();
    InputLoginCred loginCred = new InputLoginCred();
    InputNewCustomer customer = new InputNewCustomer();
    InputNewAccount account1 = new InputNewAccount();
    InputNewAccount account2 = new InputNewAccount();

    LocatorFundTransfer locatorFundTransfer = new LocatorFundTransfer();
    LocatorCustomisedStatement locatorCustomisedStatement = new LocatorCustomisedStatement();

    @BeforeClass()
    public void setBrowser(){
        browser.openBrowser("Chrome");
        webAppMethods.login(browser,loginCred.validUserId,loginCred.validPassword);
        customer.customerId = webAppMethods.createCustomer(browser);
        account1.accountId =webAppMethods.createAccount(browser,customer.customerId);
        account2.accountId = webAppMethods.createAccount(browser,customer.customerId);
    }

    @AfterClass
    public void tearDown(){
        webAppMethods.deleteAccount(browser,account1.accountId);
        webAppMethods.deleteAccount(browser,account2.accountId);
        webAppMethods.deleteCustomer(browser,customer.customerId);
        browser.driver.close();
    }

    @Test()
    public void verifyFundTransfer(){
        browser.driver.findElement(By.xpath(locatorFundTransfer.navItemFundTransfer)).click();
        browser.driver.findElement(By.xpath(locatorFundTransfer.inputPayersAccountNo)).sendKeys(account1.accountId);
        browser.driver.findElement(By.xpath(locatorFundTransfer.inputPayeesAccountNo)).sendKeys(account2.accountId);
        browser.driver.findElement(By.xpath(locatorFundTransfer.inputAmount)).sendKeys("1000");
        browser.driver.findElement(By.xpath(locatorFundTransfer.inputDescription)).sendKeys("fund transfer test");
        browser.driver.findElement(By.xpath(locatorFundTransfer.buttonSubmit)).click();
        Assert.assertTrue(browser.driver.findElement(By.xpath(locatorFundTransfer.fundTransferDetails)).isDisplayed());
    }

// Verify Fund Transfer is not done again when page is reloaded
    @Test(priority = 1)
    public void verifyPageReloadAfterFundTransfer(){
        browser.driver.navigate().refresh();
        String expectedUrl = "http://www.demo.guru99.com/V4/manager/FundTransInput.php";
        String actualUrl = browser.driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }

//    Verify transfer details appear on the Customized statement
    @Test(priority = 2)
    public void verifyCustomizedStatement(){
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.navItemCustomisedStatement)).click();
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputAccountNum)).sendKeys(account1.accountId);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String toDate= dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE,-1);
        String fromDate = dateFormat.format(cal.getTime());

        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputFromDate)).sendKeys(fromDate);
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputToDate)).sendKeys(toDate);
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.buttonSubmit)).click();

        Assert.assertTrue(browser.driver.findElement(By.xpath(locatorCustomisedStatement.secondRowCustomStatement)).isDisplayed());
    }

}
