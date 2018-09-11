package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {

    @Test(groups = "basic")
//    @Parameters({"browser"})
    public void createNewProduct() {
//        setUp(browser);
        actions.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        Actions seleniumActions = new Actions(driver) ;
        WebElement element = driver.findElement(By.xpath("//li[@data-submenu='9']"));
        seleniumActions.moveToElement(element).build().perform();
        actions.waitForContentLoad("//li[@data-submenu='10']");
        driver.findElement(By.xpath("//li[@data-submenu='10']")).click();
        actions.waitForContentLoad("//a[@id='page-header-desc-configuration-add']");
        driver.findElement(By.xpath("//a[@id='page-header-desc-configuration-add']")).click();
        ProductData data = ProductData.generate();
        actions.createProduct(data);

        driver.get(Properties.getBaseUrl());
        driver.findElement(By.className("all-product-link")).click();
        actions.waitForContentLoad("//input[@class='ui-autocomplete-input']");
        driver.findElement(By.className("ui-autocomplete-input")).sendKeys(data.getName());
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        actions.waitForContentLoad("//h1[@class='h3 product-title']");
        driver.findElement(By.xpath("//h1[@class='h3 product-title']")).click();
        WebElement productNameElement = driver.findElement(By.xpath("//h1[@itemprop='name']"));
        Assert.assertEquals(data.getName().toLowerCase(), productNameElement.getText().toLowerCase());

        String productNameClient = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        Float productPriceClient = getPriceFromClientData(driver.findElement(By.xpath("//div[@class='current-price']")).getText());
        Integer productQtyClient = getQtyFromClientData(driver.findElement(By.xpath("//div[@class='product-quantities']/span")).getText());

        ProductData clientProductData = new ProductData(productNameClient, productQtyClient, productPriceClient);

        Assert.assertEquals(clientProductData, data);

    }

    private static Integer getQtyFromClientData(String text) {
        String sCount = text.split(" ")[0];
        return Integer.parseInt(sCount);
    }

    private static Float getPriceFromClientData(String text) {
        String sAmount = text.split(" ")[0].replace(",", ".");
        return Float.parseFloat(sAmount);
    }
    // TODO implement logic to check product visibility on website
}
