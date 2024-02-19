import com.github.javafaker.Faker;
import components.Header;
import components.popups.AuthorizationPopup;
import data.countrycityes.ICityData;
import data.countrycityes.RussiaCityData;
import data.fielddata.InputFieldData;
import data.fielddata.genderdata.GenderData;
import data.language.EnglishLevelData;
import data.workformat.WorkFormatData;
import driverfactory.WebDriverFactory;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.PersonalAreaPage;

public class PersonalAreaTest {
    private Faker faker = new Faker();
    private WebDriver driver;
    private final Logger logger = LogManager.getLogger(PersonalAreaTest.class);

    public PersonalAreaTest() {
    }

    @BeforeEach
    public void init() {
        this.driver = (new WebDriverFactory(new String[]{"--start-maximized"})).create();
        this.logger.info("Start driver");
    }

    @AfterEach
    public void stopDriver() {
        if (this.driver != null) {
            this.driver.quit();
            this.logger.info("Quit driver");
        }

    }

    public void closeDriver() {
        if (this.driver != null) {
            this.driver.close();
            this.logger.info("Close driver");
        }

    }

    @Test
    public void addPersonalData() {
        (new MainPage(this.driver)).open("/");
        Header header = new Header(this.driver);
        header.clickLoginButton();
        AuthorizationPopup authorizationPopup = new AuthorizationPopup(this.driver);
        authorizationPopup.popupShouldBeVisible();
        authorizationPopup.enterEmail();
        authorizationPopup.enterPassword();
        authorizationPopup.enterLoginButton();
        header.checkLogoUser();
        header.clickPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(this.driver);
        personalAreaPage.clearFieldsData(new InputFieldData[]{InputFieldData.FNAME});
        personalAreaPage.clearFieldsData(new InputFieldData[]{InputFieldData.FNAMELATIN});
        personalAreaPage.clearFieldsData(new InputFieldData[]{InputFieldData.LNAME});
        personalAreaPage.clearFieldsData(new InputFieldData[]{InputFieldData.LNAMELATIN});
        personalAreaPage.clearFieldsData(new InputFieldData[]{InputFieldData.BLOGNAME});
        personalAreaPage.clearFieldsData(new InputFieldData[]{InputFieldData.DATEOFBRTH});
        personalAreaPage.clearFieldsCountryAndEnglish();
        personalAreaPage.addDataFields(InputFieldData.FNAME, this.faker.name().firstName());
        personalAreaPage.addDataFields(InputFieldData.FNAMELATIN, this.faker.name().lastName());
        personalAreaPage.addDataFields(InputFieldData.LNAME, this.faker.name().firstName());
        personalAreaPage.addDataFields(InputFieldData.LNAMELATIN, this.faker.name().name());
        personalAreaPage.addDataFields(InputFieldData.BLOGNAME, this.faker.name().name());
        personalAreaPage.addDataFields(InputFieldData.DATEOFBRTH, this.faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        ICityData[] cityData = RussiaCityData.values();
        ICityData city = (ICityData)this.faker.options().nextElement(cityData);
        personalAreaPage.addCountry(city);
        personalAreaPage.addCity(city);
        personalAreaPage.addEnglishlevel(EnglishLevelData.FIRSTLEVEL);
        personalAreaPage.addWillingToRelocate(true);
        personalAreaPage.addWorkFormat(true, new WorkFormatData[]{WorkFormatData.REMOTELY});
        personalAreaPage.addContactsOne(InputFieldData.SKYPE, this.faker.name().name(), 2);
        personalAreaPage.addContactsOne(InputFieldData.HABR, this.faker.name().name(), 3);
        personalAreaPage.addGender(GenderData.MALE);
        personalAreaPage.addDataFields(InputFieldData.COMPANY, this.faker.company().name());
        personalAreaPage.addDataFields(InputFieldData.POSITION, this.faker.job().position());
        personalAreaPage.clickSavePersonalArea();
        this.stopDriver();
        this.init();
        (new MainPage(this.driver)).open("/");
        header = new Header(this.driver);
        authorizationPopup = new AuthorizationPopup(this.driver);
        personalAreaPage = new PersonalAreaPage(this.driver);
        header.clickLoginButton();
        authorizationPopup.popupShouldBeVisible();
        authorizationPopup.enterEmail();
        authorizationPopup.enterPassword();
        authorizationPopup.enterLoginButton();
        header.checkLogoUser();
        header.clickPersonalArea();
        personalAreaPage.checkPersonalArea(InputFieldData.FNAME);
        personalAreaPage.checkPersonalArea(InputFieldData.FNAMELATIN);
        personalAreaPage.checkPersonalArea(InputFieldData.LNAME);
        personalAreaPage.checkPersonalArea(InputFieldData.LNAMELATIN);
        personalAreaPage.checkPersonalArea(InputFieldData.BLOGNAME);
        personalAreaPage.checkPersonalArea(InputFieldData.DATEOFBRTH);
        personalAreaPage.chechPersonalAreaData();
    }
}
