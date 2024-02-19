package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import tools.WaitTools;
import com.github.javafaker.Faker;
public abstract class AbsCommon {
    protected WebDriver driver;
    protected WaitTools waitTools;
    protected Actions actions;
    protected Faker faker;

    public AbsCommon(WebDriver driver){
        this.driver = driver;
        waitTools = new WaitTools(driver);
        this.actions = new Actions(driver);
        this.faker = new Faker();
    }
}
