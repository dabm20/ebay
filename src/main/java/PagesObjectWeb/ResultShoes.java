package PagesObjectWeb;

import helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class ResultShoes {
    protected WebDriver driver;
    Helpers help = new Helpers();

    //Constructor
    public ResultShoes(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Locations
    @FindBy(id = "w3-w0-w2-w2-0[0]")
    private WebElement txt_search_brand;

    @FindBy(id = "w3-w0-w2-w2-w6")
    private WebElement opt_puma;

    @FindBy(css = ("input[aria-label='PUMA']"))
    private WebElement opt_css_puma;

    @FindBy(xpath = "(//div[@id='w3-w0-w2-multiselect[4]'])")
    private WebElement opt_talla_10;

    @FindBy(xpath = "//h1[@class='srp-controls__count-heading']")
    private WebElement lbl_count_result;

    @FindBy(xpath = "//div[@class='srp-controls--selected-value']")
    private WebElement sel_filter;

    @FindBy(xpath = "//div[@class='srp-sort']/ul/li[position()=4]")
    private WebElement filter_low_price;

    @FindBy(xpath = "//div[@class='srp-sort']/ul/li[position()=5]")
    private WebElement filter_high_price;

    @FindBy(xpath = "//ul[@class='srp-results srp-grid clearfix']")
    private WebElement ul_results;


    // Actions
    public void txt_search_brand(String brand) {
        txt_search_brand.sendKeys(brand);
    }


    public void click_opt_puma() {
        opt_puma.click();
    }

    public void click_opt_css_puma() {
        opt_css_puma.click();
    }

    public void click_opt_talla_10() {
        opt_talla_10.click();
    }

    public void click_filter_low_price() {
        filter_low_price.click();
    }

    public void click_filter_high_price() {
        filter_low_price.click();
    }

    public String getText_lbl_count_result() {
        return lbl_count_result.getText();
    }

    public void hover_sel_filter() {
        Actions action = new Actions(driver);
        action.moveToElement(sel_filter).perform();
    }


    public double[] get_prices(int num, String currency) {
        List<WebElement> prices_product = ul_results.findElements(By.className("s-item__price"));
        double[] prices = new double[num];
        for (int i = 0; i < num; i++) {
            prices[i] = Double.parseDouble(prices_product.get(i).getText().replaceAll(currency, ""));
            //System.out.println(prices[i]);
        }
        return prices;
    }

    public HashMap<Double, String> get_products(int num, String currency) {
        List<WebElement> titles_product = ul_results.findElements(By.className("s-item__title"));
        List<WebElement> prices_product = ul_results.findElements(By.className("s-item__price"));
        HashMap<Double, String> products = new HashMap<Double, String>();
        double[] prices = new double[num];
        String[] titles = new String[num];
        for (int i = 0; i < num; i++) {
            titles[i] = titles_product.get(i).getText();
            prices[i] = Double.parseDouble(prices_product.get(i).getText().replaceAll(currency, ""));
            products.put(prices[i],titles[i]);
        }
        return products;
    }

    public String[] get_title(int num) {
        List<WebElement> titles_product = ul_results.findElements(By.className("s-item__title"));
        String[] titles = new String[num];
        for (int i = 0; i < num; i++) {
            titles[i] = titles_product.get(i).getText();
        }
        return titles;
    }
}
