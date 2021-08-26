package commonLib;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Browser {
    public WebDriver driver;
    public WebDriverWait wait;

    public void openBrowser(String bType){
        System.out.println("****************openBrowser********************");
        System.out.println("browser type---"+bType);
        try{
            switch (bType) {
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
