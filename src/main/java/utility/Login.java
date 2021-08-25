package utility;

import input.InputLoginCred;
import locator.LocatorLoginSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    public void loginAndGoToHomePage(WebDriver driver){
        driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).clear();
        driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).sendKeys(InputLoginCred.validUserId);
        //input password
        driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).clear();
        driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).sendKeys(InputLoginCred.validPassword);
        //click login button
        driver.findElement(By.xpath(LocatorLoginSection.buttonLogin)).click();
    }
}
