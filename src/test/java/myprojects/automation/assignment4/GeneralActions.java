package myprojects.automation.assignment4;


import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.get(Properties.getBaseAdminUrl());
        WebElement loginField = driver.findElement(By.id("email"));
        loginField.sendKeys("webinar.test@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();
    }

    public void createProduct(ProductData productData) {
        waitForContentLoad("//input[@id='form_step1_name_1']");
        WebElement nameField = driver.findElement(By.id("form_step1_name_1"));
        WebElement countField = driver.findElement(By.id("form_step1_qty_0_shortcut"));
        WebElement amountField = driver.findElement(By.id("form_step1_price_shortcut"));
        nameField.sendKeys(productData.getName());
        countField.sendKeys(Keys.CONTROL + "a");
        countField.sendKeys(String.valueOf(productData.getQty()));
        amountField.sendKeys(Keys.CONTROL + "a");
        amountField.sendKeys(String.valueOf(productData.getPrice()));
        driver.findElement(By.className("switch-input")).click();
        driver.findElement(By.id("submit")).click();
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad(String xpath) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }
}
