import commonLib.Browser;
import locator.LocatorLoginSection;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import url.Url;
import utility.ExcelUtils;
import utility.Screenshot;

public class LoginSectionTest {

    Browser browser = new Browser();
    Screenshot screenshot = new Screenshot();
    LocatorLoginSection locatorLoginSection = new LocatorLoginSection();
    Url url = new Url();

    @BeforeClass
    public void setUp() {
        browser.openBrowser("Chrome");
//      browser.openBrowser("Mozilla");
//        CustomScreenRecorder.startRecord("LoginSectionTest");

    }
    @AfterClass
    public void tearDown(){
        browser.driver.quit();
//        CustomScreenRecorder.stopRecord();
    }

    @Test(priority = 1, dataProvider = "Login_incorrect_credentials")
    public void verifyLoginProcess(String userName,String password,String expectedResult) throws Exception {
        //go to website
        browser.navigate(url.baseUrl);
        //input user id
        browser.driver.findElement(By.xpath(locatorLoginSection.inputUserId)).clear();
        browser.driver.findElement(By.xpath(locatorLoginSection.inputUserId)).sendKeys(userName);
        //input password
        browser.driver.findElement(By.xpath(locatorLoginSection.inputPassword)).clear();
        browser.driver.findElement(By.xpath(locatorLoginSection.inputPassword)).sendKeys(password);
        //click login button
        browser.driver.findElement(By.xpath(locatorLoginSection.buttonLogin)).click();

        try {
            //if the login failed, verify the alert text
            Alert alert = browser.driver.switchTo().alert();
            Assert.assertEquals(alert.getText(),expectedResult);
            alert.accept();

        }catch (NoAlertPresentException exception){
            //if the login passed, verify the title of the page
            Assert.assertEquals(browser.driver.getTitle(),expectedResult);
            //verify the manager id in the home page.
            Assert.assertEquals(browser.driver.findElement(By.xpath(locatorLoginSection.managerId)).getText(),"Manger Id : "+userName);
            screenshot.takeSnapShot(browser.driver,"screen-shots/title_and_managerId_verified.png");
        }
    }
    @DataProvider(name = "Login_incorrect_credentials")
    public Object[][] Authentication() {
        ExcelUtils dataconfig= new ExcelUtils("data-files/Test_data_login_banking_website.xlsx");
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
