package tests;

import PagesObjectWeb.Home;
import PagesObjectWeb.ResultShoes;
import helpers.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class testEbay {
    private WebDriver driver;
    Helpers help = new Helpers();


    @BeforeMethod
    public void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1-	Enter to Ebay
        driver.navigate().to("https://www.ebay.com/");
        help.waitImplicit(driver, 10);
    }


    @Test
    public void validateOrderPrices() {
        Home home = new Home(driver);
        ResultShoes results = new ResultShoes(driver);
        int num_products = 5;
        String currency = "S/. ";

        // 2-	Search for shoes
        home.sendKey_txt_search("shoes");
        home.click_btn_search();
        help.waitImplicit(driver, 10);


        // 3-	Select brand PUMA
        results.click_opt_css_puma();
        help.waitImplicit(driver, 10);

        // 4-	Select size 10
        results.click_opt_talla_10();
        help.waitImplicit(driver, 10);

        // 5-	Print the number of results
        System.out.println(results.getText_lbl_count_result());

        // 6-	Order by price ascendant
        results.hover_sel_filter();
        results.click_filter_low_price();
        help.waitImplicit(driver, 10);

        double[] prices_lows = results.get_prices(num_products, currency);

        // 7-	Assert the order taking the first 5 results
        for (int i = 0; i < num_products - 1; i++) {
            for (int j = i + 1; j < num_products; j++) {
                System.out.println("prices_lows[" + i + "]: " + prices_lows[i] + " <= prices_lows[" + (j) + "]: " + prices_lows[j]);
                Assert.assertTrue(prices_lows[i] <= prices_lows[j]);
            }
        }
    }


    @Test
    public void printProducts() {

        Home home = new Home(driver);
        ResultShoes results = new ResultShoes(driver);
        int num_products = 5;
        String currency = "S/. ";

        // 2-	Search for shoes
        home.sendKey_txt_search("shoes");
        home.click_btn_search();
        help.waitImplicit(driver, 10);


        // 3-	Select brand PUMA
        results.click_opt_css_puma();
        help.waitImplicit(driver, 10);

        // 4-	Select size 10
        results.click_opt_talla_10();
        help.waitImplicit(driver, 10);

        // 5-	Print the number of results
        System.out.println(results.getText_lbl_count_result());

        // 6-	Order by price ascendant
        results.hover_sel_filter();
        results.click_filter_low_price();
        help.waitImplicit(driver, 10);

        // 8-	Take the first 5 products with their prices and print them in console.
        double[] prices_lows = results.get_prices(num_products, currency);
        String[] titles_product = results.get_title(num_products);
        for (int k = 0; k < num_products; k++) {
            System.out.println("Products " + (k + 1) + ":");
            System.out.println("----------------");

            System.out.println("Descripcion: " + titles_product[k]);
            System.out.println("Precio: " + currency + prices_lows[k]);
            System.out.println("");
        }
    }


    @Test
    public void ascendantName() {
        Home home = new Home(driver);
        ResultShoes results = new ResultShoes(driver);
        int num_products = 5;
        String currency = "S/. ";

        // 2-	Search for shoes
        home.sendKey_txt_search("shoes");
        home.click_btn_search();
        help.waitImplicit(driver, 10);


        // 3-	Select brand PUMA
        results.click_opt_css_puma();
        help.waitImplicit(driver, 10);

        // 4-	Select size 10
        results.click_opt_talla_10();
        help.waitImplicit(driver, 10);

        // 5-	Print the number of results
        System.out.println(results.getText_lbl_count_result());

        // 6-	Order by price ascendant
        results.hover_sel_filter();
        results.click_filter_low_price();
        help.waitImplicit(driver, 10);
        String[] titles_product = results.get_title(num_products);
        double[] prices_lows = results.get_prices(num_products, currency);

        // 9-	Order and print the products by name (ascendant)
        Arrays.sort(titles_product);
        System.out.println("products by name (ascendant):");
        for (int k = 0; k < num_products; k++) {
            System.out.println("Product " + (k + 1) + ":");
            System.out.println("----------------");
            System.out.println("Name: " + titles_product[k]);
            System.out.println("Precio: " + currency + prices_lows[k]);
            System.out.println("");
        }
    }


    @Test
    public void descendantPrice() {
        Home home = new Home(driver);
        ResultShoes results = new ResultShoes(driver);
        int num_products = 5;
        String currency = "S/. ";

        // 2-	Search for shoes
        home.sendKey_txt_search("shoes");
        home.click_btn_search();
        help.waitImplicit(driver, 10);


        // 3-	Select brand PUMA
        results.click_opt_css_puma();
        help.waitImplicit(driver, 10);

        // 4-	Select size 10
        results.click_opt_talla_10();
        help.waitImplicit(driver, 10);

        // 5-	Print the number of results
        System.out.println(results.getText_lbl_count_result());

        // 6-	Order by price ascendant
        results.hover_sel_filter();
        results.click_filter_low_price();
        help.waitImplicit(driver, 10);
        String[] titles_product = results.get_title(num_products);
        double[] prices_lows = results.get_prices(num_products, currency);

        // 10-	Order and print the products by price in descendant mode
        help.orderDescendant(prices_lows);
        System.out.println("products by price (descendant):");
        for (int k = 0; k < num_products; k++) {
            System.out.println("Product " + (k + 1) + ":");
            System.out.println("----------------");
            System.out.println("Price: " + currency + prices_lows[k]);
            System.out.println("");
        }
    }


    @AfterMethod
    public void tearnDown() {
        driver.close();
    }
}
