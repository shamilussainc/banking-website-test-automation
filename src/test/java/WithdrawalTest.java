import commonLib.Browser;
import input.InputLoginCred;
import input.InputNewAccount;
import input.InputNewCustomer;
import input.InputWithdrawal;
import locator.LocatorBalanceEnquiry;
import locator.LocatorMiniStatement;
import locator.LocatorWithdrawal;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.WebAppMethods;

public class WithdrawalTest {
    Browser browser = new Browser();
    WebAppMethods webAppMethods = new WebAppMethods();
    InputNewCustomer customer = new InputNewCustomer();
    InputNewAccount account = new InputNewAccount();
    InputLoginCred loginCred = new InputLoginCred();
    InputWithdrawal inputWithdrawal = new InputWithdrawal();

    LocatorWithdrawal locatorWithdrawal = new LocatorWithdrawal();
    LocatorMiniStatement locatorMiniStatement = new LocatorMiniStatement();
    LocatorBalanceEnquiry locatorBalanceEnquiry = new LocatorBalanceEnquiry();


    @BeforeClass()
    public void setUp(){
        browser.openBrowser("Chrome");
        webAppMethods.login(browser,loginCred.validUserId,loginCred.validPassword);
        customer.customerId = webAppMethods.createCustomer(browser);
        account.accountId = webAppMethods.createAccount(browser,customer.customerId);
    }

    @AfterClass()
    public void tearDown(){
        webAppMethods.deleteAccount(browser,account.accountId);
        webAppMethods.deleteCustomer(browser,customer.customerId);
    }

    @Test()
    public void verifyWithdrawal(){
        webAppMethods.withdrew(browser,account,inputWithdrawal.withdrawalAmount);

        String expectedText = "Transaction details of Withdrawal for Account "+account.accountId;
        String actualText = browser.driver.findElement(By.xpath(locatorWithdrawal.titleWithdrawalDetails)).getText();
        Assert.assertEquals(actualText,expectedText);
        inputWithdrawal.transactionId = browser.driver.findElement(By.xpath(locatorWithdrawal.transactionId)).getText();
    }

//    Verify Withdrawal cannot be made if requested amount is more than current amount in the account
    @Test(priority = 1)
    public void verifyWithdrawalWithLessBalance(){
        webAppMethods.withdrew(browser,account,"100000");

        String expectedAlertText = "Transaction Failed. Account Balance Low!!!";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().accept();
    }

    @Test(priority = 2)
    public void verifyMiniStatement(){
        browser.driver.findElement(By.xpath(locatorMiniStatement.navItemMiniStatement)).click();
        browser.driver.findElement(By.xpath(locatorMiniStatement.inputAccountNum)).sendKeys(account.accountId);
        browser.driver.findElement(By.xpath(locatorMiniStatement.buttonSubmit)).click();

        String transactionId = browser.driver.findElement(By.xpath(locatorMiniStatement.transactionId)).getText();
        Assert.assertEquals(transactionId,inputWithdrawal.transactionId);
    }

    @Test(priority = 3)
    public void verifyBalance(){
        browser.driver.findElement(By.xpath(locatorBalanceEnquiry.navItemBalanceEnquiry)).click();
        browser.driver.findElement(By.xpath(locatorBalanceEnquiry.inputAccountNum)).sendKeys(account.accountId);
        browser.driver.findElement(By.xpath(locatorBalanceEnquiry.buttonSubmit)).click();

        String expectedBalance = String.valueOf(Integer.parseInt(account.initialDeposit) - Integer.parseInt(inputWithdrawal.withdrawalAmount));
        String actualBalance = browser.driver.findElement(By.xpath(locatorBalanceEnquiry.balance)).getText();
        Assert.assertEquals(actualBalance,expectedBalance);
    }

}
