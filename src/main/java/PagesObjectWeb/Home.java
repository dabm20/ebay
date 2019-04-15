package PagesObjectWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
    protected WebDriver driver;

    //Constructor
    public Home(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Locations
    @FindBy(id = "gh-ac")
    private WebElement txt_search;

    @FindBy(id = "gh-btn")
    private WebElement btn_search;

    // Actions
    public void sendKey_txt_search(String search) {
        txt_search.sendKeys(search);
    }

    public void click_btn_search() {
        btn_search.click();
    }
}
