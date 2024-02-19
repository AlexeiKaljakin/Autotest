package pages;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class AbsBasePage extends AbsCommon {
    private final String BASE_URL = System.getProperty("base.url");
    private String path = "";
    public  AbsBasePage(WebDriver driver, String path){
        super(driver);
        PageFactory.initElements(driver,this);
        this.path = path.endsWith("/") ? path.substring(0, path.length()-1): path;
    }
    public void open(String path) {
        driver.get(BASE_URL + path);
    }

    public void open(List<String> queryParams) {
        String url = BASE_URL + this.path + "?" + String.join("&", queryParams);
        driver.get(url);
    }

}
