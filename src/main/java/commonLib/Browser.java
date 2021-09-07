package commonLib;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {
//    lambda test configuration
//    public RemoteWebDriver driver = null;
//    String username = "shamilussain";
//    String accessKey = "Igxsf4XCxtXW6pcCGfZjZzqkXwzdEqt4iXPt8tPmW80vnEbucc";

    public WebDriver driver;
    public WebDriverWait wait;

    public void openBrowser(String bType){
        System.out.println("****************openBrowser********************");
        System.out.println("browser type---"+bType);
        try{
            switch (bType) {
//                case "Lambda" -> {
//                    DesiredCapabilities capabilities = new DesiredCapabilities();
//                    capabilities.setCapability("platform", "Windows 10");
//                    capabilities.setCapability("browserName", "Chrome");
//                    capabilities.setCapability("version", "92.0"); // If this cap isn't specified, it will just get the any available one
//                    capabilities.setCapability("resolution","1024x768");
//                    capabilities.setCapability("build", "First Test");
//                    capabilities.setCapability("name", "Sample Test");
//                    capabilities.setCapability("network", true); // To enable network logs
//                    capabilities.setCapability("visual", true); // To enable step by step screenshot
//                    capabilities.setCapability("video", true); // To enable video recording
//                    capabilities.setCapability("console", true); // To capture console logs
//
//                    try {
//                        driver= new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities);
//                    } catch (MalformedURLException e) {
//                        System.out.println("Invalid grid URL");
//                    }
//
//                }
                case "Mozilla" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    wait = new WebDriverWait(driver,30);
                }
                case "Chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    wait = new WebDriverWait(driver,30);
                }
                case "IE" -> {
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    wait = new WebDriverWait(driver,30);
                }
            }
        }
        catch(Exception exception){
            // any error2
            System.out.println(exception);
        }
    }

    public void navigate(String url){
        try
        {
            System.out.println("\n");
            System.out.println("----------------------------------------------------------");
            System.out.println("\n");
            driver.navigate().to(url);
        }
        catch(Exception Exception)
        {
            System.out.println(Exception);
        }
    }
}
