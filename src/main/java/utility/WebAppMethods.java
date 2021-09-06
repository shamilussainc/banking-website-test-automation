package utility;

import commonLib.Browser;
import input.InputNewAccount;
import input.InputNewCustomer;
import locator.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import url.Url;

public class WebAppMethods {

    LocatorLoginSection locatorLoginSection = new LocatorLoginSection();
    LocatorNewCustomer locatorNewCustomer = new LocatorNewCustomer();
    LocatorDeleteCustomer locatorDeleteCustomer = new LocatorDeleteCustomer();
    LocatorNewAccount locatorNewAccount = new LocatorNewAccount();
    LocatorDeleteAccount locatorDeleteAccount = new LocatorDeleteAccount();
    InputNewCustomer inputNewCustomer = new InputNewCustomer();
    InputNewAccount inputNewAccount = new InputNewAccount();
    Url url = new Url();

    public void login(Browser browser,String userId, String password){
        try {
            //gotoLoginpage
            browser.navigate(url.baseUrl);
            //input UserId
            browser.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorLoginSection.inputUserId)));
            browser.driver.findElement(By.xpath(locatorLoginSection.inputUserId)).clear();
            browser.driver.findElement(By.xpath(locatorLoginSection.inputUserId)).sendKeys(userId);
            //input password
            browser.driver.findElement(By.xpath(locatorLoginSection.inputPassword)).clear();
            browser.driver.findElement(By.xpath(locatorLoginSection.inputPassword)).sendKeys(password);
            //click login button
            browser.driver.findElement(By.xpath(locatorLoginSection.buttonLogin)).click();
        }catch (Exception e){
            System.out.println("Login failed");
            e.printStackTrace();
        }

    }


    public String createCustomer(Browser browser){
        try {
            browser.driver.findElement(By.xpath(locatorNewCustomer.navLinkNewCustomer)).click();
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

            String customerId = browser.driver.findElement(By.xpath(locatorNewCustomer.customerId)).getText();
            System.out.println("Customer created with id : "+customerId);

            return customerId;

        }catch (Exception e){
            System.out.println("Customer creation failed");
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCustomer(Browser browser, String customerId){

        try {
            browser.driver.findElement(By.xpath(locatorDeleteCustomer.navLinkDeleteCustomer)).click();
            browser.driver.findElement(By.xpath(locatorDeleteCustomer.inputCustomerId)).sendKeys(customerId);
            browser.driver.findElement(By.xpath(locatorDeleteCustomer.buttonSubmit)).click();
            String expectedAlertText = "Do you really want to delete this Customer?";
            String actualAlertText = browser.driver.switchTo().alert().getText();
            Assert.assertEquals(actualAlertText,expectedAlertText);
            browser.driver.switchTo().alert().accept();
            Alert alert = browser.driver.switchTo().alert();
            String expectedAlert = "Customer deleted Successfully";
            String actualAlert= alert.getText();
            Assert.assertEquals(actualAlert,expectedAlert);
            alert.accept();
            System.out.println("Customer with id "+customerId+" deleted");
        }catch (Exception e){
            System.out.println("Customer deletion failed");
            e.printStackTrace();
        }

    }

    public String createAccount(Browser browser,String customerId){

        try {
            JavascriptExecutor js = (JavascriptExecutor)browser.driver;
            WebElement element = browser.driver.findElement(By.xpath(locatorNewAccount.navLinkNewAccount));
            js.executeScript("arguments[0].click();", element);
            browser.driver.findElement(By.xpath(locatorNewAccount.inputCustomerId)).sendKeys(customerId);
            Select accountType = new Select(browser.driver.findElement(By.xpath(locatorNewAccount.selectAccountType)));
            accountType.selectByValue(inputNewAccount.accountType);
            browser.driver.findElement(By.xpath(locatorNewAccount.initialDeposit)).sendKeys(inputNewAccount.initialDeposit);
            browser.driver.findElement(By.xpath(locatorNewAccount.buttonSubmit)).click();

            String accountId = browser.driver.findElement(By.xpath(locatorNewAccount.accountId)).getText();
            System.out.println("Account created with id : "+accountId);
            return accountId;
        }catch (Exception e){
            System.out.println("Account creation failed!");
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAccount(Browser browser,String accountId){
        try{
            browser.driver.findElement(By.xpath(locatorDeleteAccount.navLinkDeleteAccount)).click();
            browser.driver.findElement(By.xpath(locatorDeleteAccount.inputAccountId)).sendKeys(accountId);
            browser.driver.findElement(By.xpath(locatorDeleteAccount.buttonSubmit)).click();

            String actualAlertText = browser.driver.switchTo().alert().getText();
            String expectedAlertText = "Do you really want to delete this Account?";
            Assert.assertEquals(actualAlertText,expectedAlertText);
            browser.driver.switchTo().alert().accept();
            String expectedMessage = "Account Deleted Sucessfully";
            String actualMessage = browser.driver.switchTo().alert().getText();
            Assert.assertEquals(actualMessage,expectedMessage);
            browser.driver.switchTo().alert().accept();
            System.out.println("Account with id "+accountId+" deleted");
        }catch (Exception e){
            System.out.println("Account deletion failed");
            e.printStackTrace();
        }
    }


}
