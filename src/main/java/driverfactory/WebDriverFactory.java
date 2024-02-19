package driverfactory;

import driverfactory.settings.ChromeDriverSettings;
import exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private String browserName = System.getProperty("browser.name");
    private String[] arguments;
    public WebDriverFactory(String... arguments) {
        this.arguments = arguments;
    }
    public WebDriver create() {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome": {
                return new ChromeDriver((ChromeOptions)new ChromeDriverSettings().settings());
            }
        }
        throw new BrowserNotSupportedException(browserName);
    }

}
