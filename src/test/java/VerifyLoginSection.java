import commonLib.Browser;
import input.InputLoginCred;
import locator.LocatorLoginSection;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import url.Url;
import utility.ExcelUtils;

public class VerifyLoginSection {

    @BeforeClass
    public static void openBrowser(){
        Browser.openBrowser("Chrome");
//        Browser.openBrowser("Mozilla");
    }
    @Test(priority = 1)
    public static void enterValidUserIdAndPassword(){
        //go to website
        Browser.navigate(Url.baseUrl);
        //input user id
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).clear();
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).sendKeys(InputLoginCred.validUserId);
        //input password
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).clear();
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).sendKeys(InputLoginCred.validPassword);
        //click login button
        Browser.driver.findElement(By.xpath(LocatorLoginSection.buttonLogin)).click();
        //Verify title of the home page
        Assert.assertEquals(Browser.driver.getTitle(),"Guru99 Bank Manager HomePage");
    }


    @Test(priority = 2, dataProvider = "Login_incorrect_credentials")
    public static void enterIncorrectLogCred(String userName,String password,String expectedResult){
        //go to website
        Browser.navigate(Url.baseUrl);
        //input user id
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).clear();
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputUserId)).sendKeys(userName);
        //input password
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).clear();
        Browser.driver.findElement(By.xpath(LocatorLoginSection.inputPassword)).sendKeys(password);
        //click login button
        Browser.driver.findElement(By.xpath(LocatorLoginSection.buttonLogin)).click();
        //verify the alert message
        Assert.assertEquals(Browser.driver.switchTo().alert().getText(),expectedResult);
    }


    @DataProvider(name = "Login_incorrect_credentials")
    public Object[][] Authentication() throws Exception{
        ExcelUtils dataconfig= new ExcelUtils("/home/shamil/Documents/test data (do not delete)/Test_data_login_banking_website.xlsx");
        int rows=dataconfig.getRowCount(0);
        Object[][] data=new Object[rows-1][3];
        for (int i=0;i<rows-1;i++)
        {
            data[i][0]=dataconfig.getData(0,i+1,2);
            data[i][1]=dataconfig.getData(0,i+1, 3);
            data[i][2]=dataconfig.getData(0,i+1,4);
        }
        return data;
    }

}
