import commonLib.Browser;
import input.InputEditCustomer;
import input.InputLoginCred;
import input.InputNewCustomer;
import locator.LocatorEditCustomer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.WebAppMethods;

public class EditCustomerTest {
    Browser browser = new Browser();
    WebAppMethods webAppMethods = new WebAppMethods();
    InputLoginCred loginCred = new InputLoginCred();
    InputNewCustomer customer = new InputNewCustomer();
    InputEditCustomer inputEditCustomer = new InputEditCustomer();

    LocatorEditCustomer locatorEditCustomer = new LocatorEditCustomer();


    @BeforeClass()
    public void setUp(){
        browser.openBrowser("Chrome");
        webAppMethods.login(browser,loginCred.validUserId,loginCred.validPassword);
        customer.customerId = webAppMethods.createCustomer(browser);
    }

    @AfterClass()
    public void tearDown(){
        webAppMethods.deleteCustomer(browser,customer.customerId);
        browser.driver.close();
    }

    @Test()
    public void verifyEditCustomer(){
        browser.driver.findElement(By.xpath(locatorEditCustomer.navLinkEditCustomer)).click();
        browser.driver.findElement(By.xpath(locatorEditCustomer.inputCustomerId)).sendKeys(customer.customerId);
        browser.driver.findElement(By.xpath(locatorEditCustomer.buttonSubmit)).click();

        WebElement inputAddress = browser.driver.findElement(By.xpath(locatorEditCustomer.inputAddress));
        inputAddress.clear();
        inputAddress.sendKeys(inputEditCustomer.address);

        WebElement inputCity = browser.driver.findElement(By.xpath(locatorEditCustomer.inputCity));
        inputCity.clear();
        inputCity.sendKeys(inputEditCustomer.city);


        WebElement inputState = browser.driver.findElement(By.xpath(locatorEditCustomer.inputState));
        inputState.clear();
        inputState.sendKeys(inputEditCustomer.state);

        WebElement inputPin = browser.driver.findElement(By.xpath(locatorEditCustomer.inputPin));
        inputPin.clear();
        inputPin.sendKeys(inputEditCustomer.pin);

        WebElement inputMobileNumber = browser.driver.findElement(By.xpath(locatorEditCustomer.inputMobileNumber));
        inputMobileNumber.clear();
        inputMobileNumber.sendKeys(inputEditCustomer.mobileNumber);

        WebElement inputEmail = browser.driver.findElement(By.xpath(locatorEditCustomer.inputEmail));
        inputEmail.clear();
        inputEmail.sendKeys(inputEditCustomer.email);

        browser.driver.findElement(By.xpath(locatorEditCustomer.buttonSubmitEditCustomerForm)).click();

        Assert.assertTrue(browser.driver.findElement(By.xpath(locatorEditCustomer.successMessageEditCustomer)).isDisplayed());
    }
}
