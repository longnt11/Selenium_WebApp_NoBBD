package AssignmentFinal.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private final JavascriptExecutor javascriptExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.javascriptExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Actions getActions() {
        return actions;
    }

    public JavascriptExecutor getJavascriptExecutor() {
        return javascriptExecutor;
    }

    // Common Elements
    @FindBy(id = "PRData_processing")
    private WebElement processingBar; // loading process

    @FindBy(id = "ajaxCall")
    private WebElement processingIcon; // loading process

    @FindBy(id = "loading")
    private WebElement loadingBar; // loading process

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    private WebElement errorMsg;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successMessage;

    // Common Functions
    public boolean verifySuccessMsg(String successMsg) {
        getWait().until(ExpectedConditions.visibilityOf(successMessage));
        return getElementTextValueViaInnerText(successMessage).contains(successMsg);
    }

    public boolean verifyFailMsg(String failMsg) {
        getWait().until(ExpectedConditions.visibilityOf(errorMsg));
        return getElementTextValueViaInnerText(errorMsg).contains(failMsg);
    }

    /**
     * Verify single element display on DOM after waiting element to be present
     *
     * @param element WebElement
     * @return true if element is displayed and vice versa
     */
    public boolean isElementDisplay(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    /**
     * Verify all elements in a list are displayed on DOM after waiting elements to be present
     *
     * @param elementList Elements in a List
     * @return true if all elements in a list are found
     */
    public boolean isElementDisplay(List<WebElement> elementList) {
        boolean found = false;
        getWait().until(ExpectedConditions.visibilityOfAllElements(elementList));
        for (WebElement e : elementList) {
            if (e.isDisplayed()) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Verify all elements passed in this method are displayed on DOM after waiting elements to be present
     *
     * @param elements Elements in a List
     * @return true if all elements  are found
     */
    public boolean isElementDisplay(WebElement... elements) {
        boolean found = false;
        getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
        for (WebElement e : elements) {
            if (e.isDisplayed()) {
                found = true;
            }
        }
        return found;
    }

    /**
     * CLick element
     * @param element element to be clicked
     */
    public void clickTo(WebElement element) {
        // wait for loading process to finish
        getWait().until(ExpectedConditions.invisibilityOf(loadingBar));
        // Scroll to element using JS to handle firefox browser
        String jsScript ="arguments[0].scrollIntoView({ behavior: 'instant', block: 'center' });";
        getJavascriptExecutor().executeScript(jsScript, element);
        // Move offset to avoid button being obscured by another element
        getActions().moveToElement(element, 0, 20).perform();
        // Wait for element to be clickable
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Input text
     * @param element element in which to input text
     * @param text String of text to input
     */
    public void inputText(WebElement element, String text) {
        // wait for loading process to finish
        getWait().until(ExpectedConditions.invisibilityOf(loadingBar));
        element.clear();
        element.sendKeys(text);
        // wait for loading process to finish after searching
        getWait().until(ExpectedConditions.invisibilityOfAllElements(processingBar, processingIcon));
    }

    /**
     * Find element WebElement by using dynamic xpath
     *
     * @param text text of left menu link
     * @return WebElement of Left menu link
     */
    public WebElement findElementByDynamicXpath(String dynamicXpath, String text) {
        return getDriver().findElement(By.xpath(String.format(dynamicXpath, text)));
    }

    /**
     * Get text of an element and trim leading and trailing spaces
     * @param element element to get text
     * @return String of text of element
     */
    public String getElementTextValueViaInnerText(WebElement element) {
        return element.getAttribute("innerText").trim();
    }

    /**
     * Find option in combobox by using parent parentElement and through dynamic Xpath.
     * This method is applicable to 2 pages: Add Purchase List page & Add Sale page
     * @param parentElement Parent element that contains all options in combobox
     * @param option        option name
     * @return Element as option in combobox
     */
    private WebElement findComboOption(WebElement parentElement, String option) {
        String dynamicXpath = "//li[normalize-space()='%s']";
        String childElementXpath = String.format(dynamicXpath, option);
        // Using parent parentElement to find child parentElement
        return parentElement.findElement(By.xpath(childElementXpath));
    }

    /**
     * Choose option in combobox by clicking option in dropdown list
     * @param comboboxField combo box field to click to start selecting options
     * @param comboboxOption element contains all option values of combobox
     * @param option option of combobox to select
     */
    public void chooseComboOption(WebElement comboboxField, WebElement comboboxOption, String option) {
        clickTo(comboboxField);
        clickTo(findComboOption(comboboxOption, option));
    }

    /**
     * Choose option in combobox by searching in a search box
     * @param comboboxField combo box field to click to start selecting options
     * @param comboboxOption element contains all option values of combobox
     * @param option option of combobox to select
     */
    public void chooseComboOptionViaSearch(WebElement comboboxField, WebElement comboboxOption, String option){
        clickTo(comboboxField);
        inputText(comboboxOption, option);
        comboboxOption.sendKeys(Keys.ENTER);
    }

    /**
     * Scroll to footer
     */
    public void scrollToFooter(){
        WebElement footer = getDriver().findElement(By.xpath("//footer"));
        // Calculate the desiredCapabilities vertical offset
        int deltaY = footer.getRect().y;
        getActions().scrollByAmount(0, deltaY).perform();
    }
}