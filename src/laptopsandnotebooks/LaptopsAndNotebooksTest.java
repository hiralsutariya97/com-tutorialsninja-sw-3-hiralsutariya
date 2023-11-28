package laptopsandnotebooks;
/**
 * Create the class LaptopsAndNotebooksTest
 * Write the following test
 * 1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
 * 1.1 Mouse hover on Laptops & Notebooks Tab.and click
 * 1.2 Click on “Show All Laptops & Notebooks”
 * 1.3 Select Sort By "Price (High > Low)"
 * 1.4 Verify the Product price will arrange in High to Low order.
 * 2. Test name verifyThatUserPlaceOrderSuccessfully()
 * 2.1 Mouse hover on Laptops & Notebooks Tab and click
 * 2.2 Click on “Show All Laptops & Notebooks”
 * 2.3 Select Sort By "Price (High > Low)"
 * 2.4 Select Product “MacBook”
 * 2.5 Verify the text “MacBook”
 * 2.6 Click on ‘Add To Cart’ button
 * 2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
 * 2.8 Click on link “shopping cart” display into success message
 * 2.9 Verify the text "Shopping Cart"
 * 2.10 Verify the Product name "MacBook"
 * 2.11 Change Quantity "2"
 * 2.12 Click on “Update” Tab
 * 2.13 Verify the message “Success: You have modified your shopping cart!”
 * 2.14 Verify the Total £737.45
 * 2.15 Click on “Checkout” button
 * 2.16 Verify the text “Checkout”
 * 2.17 Verify the Text “New Customer”
 * <p>
 * 2.18 Click on “Guest Checkout” radio button
 * 2.19 Click on “Continue” tab
 * 2.20 Fill the mandatory fields
 * 2.21 Click on “Continue” Button
 * 2.22 Add Comments About your order into text area
 * 2.23 Check the Terms & Conditions check box
 * 2.24 Click on “Continue” button
 * 2.25 Verify the message “Warning: Payment method required!”
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.time.Duration;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverAndClick(By.xpath("//a[text()='Laptops & Notebooks']"));

        //1.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[text()='Laptops & Notebooks']"));

        //1.3 Select Sort By "Price (High > Low)"
        WebElement dropDown = driver.findElement(By.xpath("//select[@id='input-sort']"));
        Select select = new Select(dropDown);
        select.selectByVisibleText(" Default ");
        selectByVisibleTextFromDropDown(By.className("form-control"), "Price (High > Low)");
        select.selectByValue("https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC");

        //1.4 Verify the Product price will arrange in High to Low order.
        List<WebElement> element1 = driver.findElements(By.xpath("//p[@class= 'price']"));
        for (WebElement result : element1) {
            System.out.println(result.getText());
        }

    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverAndClick(By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Laptops & Notebooks']"));

        //2.2 Click on “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");

        //2.3 Select Sort By "Price (High > Low)"
        WebElement dropDown = driver.findElement(By.xpath("//select[@id='input-sort']"));
        Select select = new Select(dropDown);
        select.selectByVisibleText(" Default ");
        selectByVisibleTextFromDropDown(By.className("form-control"), "Price (High > Low)");
        select.selectByValue("https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC");


        //  2.4 Select Product “MacBook”
        findElementFromWebPage(By.xpath("//a[normalize-space()='MacBook']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// 2.5 Verify the text “MacBook”
        Assert.assertEquals("MacBook", getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']")));

        // 2.6 Click on ‘Add To Cart’ button
        findElementFromWebPage(By.xpath("//button[@id='button-cart']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        Assert.assertEquals("Success: You have added MacBook to your shopping cart!\n" +
                "×", getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));

        //2.8 Click on link “shopping cart” display into success message
        findElementFromWebPage(By.xpath("//a[normalize-space()='shopping cart']")).click();

        //2.9 Verify the text "Shopping Cart
        Assert.assertEquals("Shopping Cart  (0.00kg)", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")));

        // 2.10 Verify the Product name "MacBook"
        Assert.assertEquals("MacBook\n" + "Reward Points: 600", getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]")));

        // 2.11 Change Quantity "2"
        driver.findElement(By.xpath("//input[@class='form-control']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        sendTextToElement(By.xpath("//input[@class='form-control']"), "2");

        //2.12 Click on “Update” Tab
        findElementFromWebPage(By.xpath("//i[@class='fa fa-refresh']")).click();

        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("Success: You have modified your shopping cart!\n" + "×", getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));

        //2.14 Verify the Total $1,204.00
        Assert.assertEquals("$1,204.00", getTextFromElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[2]")));

        //2.15 Click on “Checkout” button
        findElementFromWebPage(By.xpath("//a[@class='btn btn-primary']")).click();

        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Checkout", getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']")));

        // 2.17 Verify the Text “New Customer”
        Assert.assertEquals("New Customer", getTextFromElement(By.id("//h2[normalize-space()='New Customer']")));

        //2.18 Click on “Guest Checkout” radio button
        findElementFromWebPage(By.xpath("//input[@value='guest']")).click();

        //2.19 Click on “Continue” tab
        findElementFromWebPage(By.xpath("//input[@id='button-account']")).click();

        //2.20 Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='input-payment-firstname']"), "Michel");
        sendTextToElement(By.xpath("//input[@id='input-payment-lastname']"), "Cors");
        sendTextToElement(By.xpath("//input[@id='input-payment-email']"), "michelcors@gmail.com");
        sendTextToElement(By.xpath("//input[@id='input-payment-telephone']"), "0776859453");
        sendTextToElement(By.xpath("//input[@id='input-payment-address-1']"), "Coral Street");
        sendTextToElement(By.xpath("//input[@id='input-payment-city']"), "Sidcup");
        sendTextToElement(By.xpath("//input[@id='input-payment-postcode']"), "DA1 4ED");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement dropDown3 = driver.findElement(By.xpath("//select[@id='input-payment-zone']"));
        Select select3 = new Select(dropDown3);
        select3.selectByVisibleText("Buckinghamshire");

        //2.21 Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));

        //2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "Please leave order at neighbours");

        //2.23 Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@id='button-shipping-method']"));

        //2.24 Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-shipping-method']"));

        //2.25 Verify the message “Warning: Payment method required!”
        Assert.assertEquals(" Warning: Payment method required! ", getTextFromElement(By.xpath("//div[contains(text(), 'Warning: Payment method required!')]")));

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
