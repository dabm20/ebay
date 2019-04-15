package helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Helpers {
    public void waitSeconds(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitImplicit(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void scrollWebElement(WebDriver driver, WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeAsyncScript("arguments[0].scrollIntoView();", webElement);
    }

    public void orderDescendant(double[] prices_lows) {
        Double aux;
        for (int i = 0; i < prices_lows.length; i++) {
            for (int j = i + 1; j < prices_lows.length; j++) {
                if (prices_lows[i] < prices_lows[j]) {
                    aux = prices_lows[i];
                    prices_lows[i] = prices_lows[j];
                    prices_lows[j] = aux;
                }
            }
        }
    }
}
