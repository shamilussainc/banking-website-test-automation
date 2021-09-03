import commonLib.Browser;
import input.InputLoginCred;
import input.InputNewAccount;
import locator.LocatorBalanceEnquiry;
import locator.LocatorCustomisedStatement;
import locator.LocatorDeleteAccount;
import locator.LocatorMiniStatement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.Login;

//SM6
//Delete Account & check  Mini Statement, Balance Enquiry
public class DeleteAccountTest {
    Browser browser = new Browser();
    Login login = new Login();

    LocatorDeleteAccount locatorDeleteAccount = new LocatorDeleteAccount();
    LocatorMiniStatement locatorMiniStatement = new LocatorMiniStatement();
    LocatorBalanceEnquiry locatorBalanceEnquiry = new LocatorBalanceEnquiry();
    LocatorCustomisedStatement locatorCustomisedStatement = new LocatorCustomisedStatement();

    InputLoginCred inputLoginCred = new InputLoginCred();


    @BeforeClass()
    public void setBrowser(){
        browser.openBrowser("Chrome");
        login.login(browser,inputLoginCred.validUserId,inputLoginCred.validPassword);
    }

    @AfterClass()
    public void tearDown(){
        browser.driver.close();
    }

//Verify confirmation message is shown on deletion of an account
    @Test()
    public void verifyConfirmationMessage  (){
        browser.driver.findElement(By.xpath(locatorDeleteAccount.navLinkDeleteAccount)).click();
        browser.driver.findElement(By.xpath(locatorDeleteAccount.inputAccountId)).sendKeys(InputNewAccount.accountId);
        browser.driver.findElement(By.xpath(locatorDeleteAccount.buttonSubmit)).click();

        String actualAlertText = browser.driver.switchTo().alert().getText();
        String expectedAlertText = "Do you really want to delete this Account?";
        Assert.assertEquals(actualAlertText,expectedAlertText);

    }

//    Verify system behaviour after Account is deleted
    @Test(priority = 1)
    public void verifySystemBehaviour(){
        browser.driver.switchTo().alert().accept();
        String expectedMessage = "Account Deleted Sucessfully";
        String actualMessage = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualMessage,expectedMessage);
        browser.driver.switchTo().alert().accept();
        String expectedUrl = "http://www.demo.guru99.com/V4/manager/Managerhomepage.php";
        String actualUrl = browser.driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }

//    Verify that mini statement is not generated for a deleted account
    @Test(priority = 2)
    public void verifyMiniStatementForDeletedAccount(){
        browser.driver.findElement(By.xpath(locatorMiniStatement.navItemMiniStatement)).click();
        browser.driver.findElement(By.xpath(locatorMiniStatement.inputAccountNum)).sendKeys(InputNewAccount.accountId);
        browser.driver.findElement(By.xpath(locatorMiniStatement.buttonSubmit)).click();

        String expectedAlertText = "Account does not exist";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().accept();
    }

    @Test(priority = 3)
    public void verifyBalanceForDeletedAccount(){
        browser.driver.findElement(By.xpath(locatorBalanceEnquiry.navItemBalanceEnquiry)).click();
        browser.driver.findElement(By.xpath(locatorBalanceEnquiry.inputAccountNum)).sendKeys(InputNewAccount.accountId);
        browser.driver.findElement(By.xpath(locatorBalanceEnquiry.buttonSubmit)).click();

        String expectedAlertText = "Account does not exist";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().accept();

        String expectedUrl = "http://www.demo.guru99.com/V4/manager/BalEnqInput.php";
        String actualUrl = browser.driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }

//    Verify that customized statement is not generated for deleted account
    @Test(priority = 4)
    public void verifyCustomizedStatement(){
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.navItemCustomisedStatement)).click();
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputAccountNum)).sendKeys(InputNewAccount.accountId);
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputFromDate)).sendKeys("13/8/2021");
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputToDate)).sendKeys("10/08/2021");
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputMinTransaction)).sendKeys("1000");
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.inputNumOfTransaction)).sendKeys("10");
        browser.driver.findElement(By.xpath(locatorCustomisedStatement.buttonSubmit)).click();

        String expectedAlertText = "Account does not exist";
        String actualAlertText = browser.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText,expectedAlertText);
        browser.driver.switchTo().alert().accept();

        String expectedUrl = "http://www.demo.guru99.com/V4/manager/CustomisedStatementInput.php";
        String actualUrl = browser.driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);

    }

}
