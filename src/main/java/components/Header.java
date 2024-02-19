package components;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Header extends AbsCommon {
    private String iconUserSelector = "img[src*='blue-owl']";

    public Header(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        String loginButton = "//button[text()='Войти']";
        this.waitTools.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.xpath(loginButton)));
        this.waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath(loginButton)));
        WebElement enterButton = this.driver.findElement(By.xpath(loginButton));
        enterButton.click();
    }

    public void checkLogoUser() {
        this.waitTools.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.iconUserSelector)));
    }

    public void clickPersonalArea() {
        this.driver.findElement(By.xpath("//div[@class='sc-1ceoo74-0 kwfuzG']")).click();
        this.driver.findElement(By.xpath("//*[contains(@href, '/lk/biography/personal')]")).click();
    }
}