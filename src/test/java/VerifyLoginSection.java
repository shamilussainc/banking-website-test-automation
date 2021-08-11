import commonLib.Browser;
import input.InputLoginCred;
import locator.LocatorLoginSection;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import url.Url;

public class VerifyLoginSection {

    @BeforeClass
    public static void openBrowser(){
//        Browser.openBrowser("Chrome");
        Browser.openBrowser("Mozilla");
    }
    @Test(priority = 1)
    public static void enterValidUserIdAndPassword(){
        //go to website
        Browser.navigate(Url.baseUrl);
        //input user id
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).sendKeys(InputLoginCred.validUserId);
        //input password
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).sendKeys(InputLoginCred.validPassword);
        //click login button
        Browser.driver.findElement(By.xpath(LocatorLoginSection.buttonLogin)).click();
        //Verify title of the home page
        Assert.assertEquals(Browser.driver.getTitle(),"Guru99 Bank Manager HomePage");
    }
}
