package utility;

import commonLib.Browser;
import input.InputLoginCred;
import locator.LocatorLoginSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import url.Url;

public class Login {
    LocatorLoginSection locatorLoginSection = new LocatorLoginSection();
    Url url = new Url();

    public void login(Browser browser,String userId, String password){
        //gotoLoginpage
        browser.navigate(url.baseUrl);
        //input UserId
        browser.driver.findElement(By.xpath(locatorLoginSection.inputUserId)).clear();
        browser.driver.findElement(By.xpath(locatorLoginSection.inputUserId)).sendKeys(userId);
        //input password
        browser.driver.findElement(By.xpath(locatorLoginSection.inputPassword)).clear();
        browser.driver.findElement(By.xpath(locatorLoginSection.inputPassword)).sendKeys(password);
        //click login button
        browser.driver.findElement(By.xpath(locatorLoginSection.buttonLogin)).click();
    }
}
