import commonLib.Browser;
import input.InputLoginCred;
import input.InputNewAccount;
import input.InputNewCustomer;
import locator.LocatorDeposit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.WebAppMethods;

public class DepositTest {
    Browser browser = new Browser();
    WebAppMethods webAppMethods = new WebAppMethods();
    InputLoginCred loginCred = new InputLoginCred();
    InputNewCustomer customer = new InputNewCustomer();
    InputNewAccount account = new InputNewAccount();

    LocatorDeposit locatorDeposit = new LocatorDeposit();


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

//    Verify deposit can be made to another account
    @Test()
    public void verifyDepositToAnotherAccount(){
        browser.driver.findElement(By.xpath(locatorDeposit.navLinkDeposit)).click();
        browser.driver.findElement(By.xpath(locatorDeposit.accountNo)).sendKeys(account.accountId);
        browser.driver.findElement(By.xpath(locatorDeposit.amount)).sendKeys("1000");
        browser.driver.findElement(By.xpath(locatorDeposit.description)).sendKeys("fund deposit");
        browser.driver.findElement(By.xpath(locatorDeposit.buttonSubmit)).click();

        String expectedText = "Transaction details of Deposit for Account "+account.accountId;
        String actualText = browser.driver.findElement(By.xpath(locatorDeposit.titleTransactionDetails)).getText();
        Assert.assertEquals(actualText,expectedText);
    }

//    Verify Deposit cannot be made by reload the Deposit detail page
    @Test(priority = 1)
    public void verifyReloadAfterDeposit(){
        browser.driver.navigate().refresh();

        String expectedCurrentUrl = "http://www.demo.guru99.com/V4/manager/DepositInput.php";
        String actualCurrentUrl = browser.driver.getCurrentUrl();
        Assert.assertEquals(actualCurrentUrl,expectedCurrentUrl);

    }
}
