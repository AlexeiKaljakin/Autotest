package pages;

import common.AbsCommon;
import data.countrycityes.ICityData;
import data.fielddata.InputFieldData;
import data.fielddata.genderdata.GenderData;
import data.language.EnglishLevelData;
import data.workformat.WorkFormatData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAreaPage extends AbsCommon {
    private Logger logger = LogManager.getLogger(PersonalAreaPage.class);

    public PersonalAreaPage(WebDriver driver) {
        super(driver);
    }

    public void clearFieldsData(InputFieldData... inputFieldData) {
        InputFieldData[] var2 = inputFieldData;
        int var3 = inputFieldData.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            InputFieldData fieldData = var2[var4];
            this.driver.findElement(By.cssSelector(String.format("input[name='%s']", fieldData.getName()))).clear();
        }

        this.logger.info("Fields cleared");
    }

    public void clearFieldsCountryAndEnglish() {
        this.driver.findElement(By.xpath("//div[@data-num='0']/div/div/button[@type='button']")).click();
        this.driver.findElement(By.xpath("//div[@data-num='1']/div/div/button[@type='button']")).click();
        this.driver.findElement(By.cssSelector(".js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        this.driver.findElement(By.xpath("//button[@data-value='' and @data-empty='Не указано' and @title='Не выбрано']")).click();
        this.driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).click();
        this.driver.findElement(By.xpath("//div[@class='lk-cv-block__select-options js-custom-select-options-container']/div/button[@title='Не выбрано']")).click();
    }

    public void addDataFields(InputFieldData inputFieldData, String data) {
        this.driver.findElement(By.cssSelector(String.format("input[name='%s']", inputFieldData.getName()))).sendKeys(new CharSequence[]{data});
    }

    public void addCountry(ICityData cityData) {
        WebElement russiaSelectElement = this.driver.findElement(By.cssSelector("[data-slave-selector='.js-lk-cv-dependent-slave-city']"));
        russiaSelectElement.click();
        WebElement countryListContainer = russiaSelectElement.findElement(By.xpath(".//*[contains(@class, 'js-custom-select-options-container')]"));
        this.waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeContains(countryListContainer, "class", "hide")));
        this.driver.findElement(By.cssSelector(String.format("[title='%s']", cityData.getCountryData().getNameCountry()))).click();
        this.waitTools.waitForCondition(ExpectedConditions.attributeContains(countryListContainer, "class", "hide"));
    }

    public void addCity(ICityData cityData) {
        this.waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-title='Город']")));
        WebElement city = this.driver.findElement(By.xpath("//*[contains(@class, 'js-lk-cv-dependent-slave-city')]"));
        city.click();
        this.driver.findElement(By.cssSelector(String.format("[title='%s']", cityData.getName()))).click();
    }

    public void addEnglishlevel(EnglishLevelData englishLevelData) {
        WebElement englishlevel = this.driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]"));
        englishlevel.click();
        this.driver.findElement(By.cssSelector(String.format("[title*='%s']", englishLevelData.getEnglishLevel()))).click();
    }

    public void addWillingToRelocate(boolean isSelected) {
        String relocate = isSelected ? "Да" : "Нет";
        this.driver.findElement(By.xpath(String.format("//span[@class=\"radio__label\" and text()=\"%s\"]", relocate))).click();
    }

    public void addWorkFormat(boolean isSelected, WorkFormatData... workFormats) {
        WorkFormatData[] var3 = workFormats;
        int var4 = workFormats.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            WorkFormatData workFormatData = var3[var5];
            this.waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Удаленно']")));
            WebElement inputSelect = this.driver.findElement(By.cssSelector(String.format("input[title='%s']", workFormatData.getName())));
            if (inputSelect.isSelected() != isSelected) {
                inputSelect.click();
            }
        }

    }

    public void clickAddCommunicationMethod() {
        this.driver.findElement(By.cssSelector("button.js-lk-cv-custom-select-add")).click();
    }

    public void addContactsOne(InputFieldData inputFieldData, String data, int number) {
        this.driver.findElement(By.xpath("//button[@type='button' and text()='Добавить']")).click();
        this.waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"placeholder\" and text()=\"Способ связи\"]")));
        this.driver.findElement(By.xpath("//span[@class=\"placeholder\" and text()=\"Способ связи\"]")).click();
        this.driver.findElement(By.xpath(String.format("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']/div/button[@data-value='%s']", inputFieldData.getName()))).click();
        WebElement inputData = this.driver.findElement(By.id(String.format("id_contact-%s-value", number)));
        this.waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(inputData));
        inputData.click();
        inputData.sendKeys(new CharSequence[]{data});
        this.logger.info("Добавлен 1 контакт");
        this.driver.findElement(By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add")).click();
    }

    public void addGender(GenderData genderData) {
        this.driver.findElement(By.id("id_gender")).click();
        this.driver.findElement(By.cssSelector(String.format("option[value='%s']", genderData.getName()))).click();
    }

    public void clickSavePersonalArea() {
        this.driver.findElement(By.cssSelector("button[name='continue']")).click();
        WebElement saveText = this.driver.findElement(By.xpath("//div[@class='container container-padding-top-half container-padding-bottom-half']/span/span"));
        String actualText = saveText.getText().trim();
        Assertions.assertEquals("Данные успешно сохранены", actualText);
    }

    public void checkPersonalArea(InputFieldData inputFieldData) {
        Assertions.assertTrue(!this.driver.findElement(By.cssSelector(String.format("input[name='%s']", inputFieldData.getName()))).getAttribute("value").isEmpty());
    }

    public void chechPersonalAreaData() {
        Assertions.assertTrue(!this.driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!this.driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText().isEmpty());
        Assertions.assertTrue(!this.driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText().isEmpty());
        Assertions.assertTrue(!this.driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).getText().isEmpty());
        Assertions.assertTrue(this.driver.findElement(By.xpath("//input[@id='id_ready_to_relocate_1']")).isSelected());
        Assertions.assertTrue(this.driver.findElement(By.cssSelector("input[title='Удаленно']")).isSelected());
        Assertions.assertTrue(!this.driver.findElement(By.id("id_contact-0-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!this.driver.findElement(By.id("id_contact-1-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!this.driver.findElement(By.id("id_gender")).getAttribute("value").isEmpty());
    }
}