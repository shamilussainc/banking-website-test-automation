import commonLib.Browser;
import input.InputLoginCred;
import locator.LocatorLoginSection;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyLoginSection {

    @BeforeClass
    public static void openBrowser(){
        Browser.openBrowser("Mozilla");
    }
    @Test(priority = 1)
    public static void enterValidUserIdAndPassword(){
        Browser.navigate("http://www.demo.guru99.com/V4/");
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).sendKeys(InputLoginCred.validUserId);
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).sendKeys(InputLoginCred.validPassword);
        Browser.driver.findElement(By.xpath(LocatorLoginSection.buttonLogin)).click();
    }
}
