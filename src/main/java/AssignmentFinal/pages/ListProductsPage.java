package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ListProductsPage extends BasePage {
    public ListProductsPage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(xpath = "//label[normalize-space()='Search']//input")
    private WebElement searchField;

    @FindBy(id = "myModal")
    private WebElement productDetailPopup;

    @FindBy(id = "pr-image")
    private WebElement popupProductImage;

    @FindBy(xpath = "//img[contains(@class,'bcimg')]")
    private WebElement popupProductBarcodeImg;

    @FindBy(xpath = "//img[contains(@class,'qrimg')]")
    private WebElement popupProductQrCodeImg;

    @FindBy(xpath = "//div[normalize-space()='Product Details']//following-sibling::div")
    private WebElement popupProductDetails;

    private WebElement tableRowProductName(String productName) {
        String dynamicXpath = "//td[normalize-space()='%s']";
        return findElementByDynamicXpath(dynamicXpath, productName);
    }

    private WebElement tableRowActionsBtn(String productName) {
        String dynamicXpath = "//td[normalize-space()='%s']//following-sibling::td//button[normalize-space()='Actions']";
        return findElementByDynamicXpath(dynamicXpath, productName);
    }

    private WebElement tableRowEditBtn(String productName) {
        String dynamicXpath = "//td[normalize-space()='%s']//following-sibling::td//a[normalize-space()='Edit Product']";
        return findElementByDynamicXpath(dynamicXpath, productName);
    }

    private WebElement popupProductInfo(String productDetailInfo) {
        String dynamicXpath = "//td[normalize-space()='%s']//following-sibling::td";
        return findElementByDynamicXpath(dynamicXpath, productDetailInfo);
    }

    private WebElement popupProductType() {
        return popupProductInfo("Type");
    }

    private WebElement popupProductName() {
        return popupProductInfo("Name");
    }

    private WebElement popupProductCode() {
        return popupProductInfo("Code");
    }

    private WebElement popupProductCategory() {
        return popupProductInfo("Category");
    }

    private WebElement popupProductUnit() {
        return popupProductInfo("Unit");
    }

    private WebElement popupProductCost() {
        return popupProductInfo("Cost");
    }

    private WebElement popupProductPrice() {
        return popupProductInfo("Price");
    }

    private WebElement popupProductTaxRate() {
        return popupProductInfo("Tax Rate");
    }

    private WebElement popupProductTaxMethod() {
        return popupProductInfo("Tax Method");
    }

    private WebElement popupProductAlertQuantity() {
        return popupProductInfo("Alert Quantity");
    }

    /**
     * Find table column name by using dynamic xpath
     *
     * @param columnName name of column
     * @return WebElement of table column
     */
    private WebElement tableColumnName(String columnName) {
        String dynamicXpath = "//thead//th[normalize-space()='%s']";
        return findElementByDynamicXpath(dynamicXpath, columnName);
    }

    // Functions
    public boolean verifyTableColumnName(String... columnName) {
        boolean found = false;
        for (String name : columnName) {
            if (isElementDisplay(tableColumnName(name))) {
                found = true;
            }
        }
        return found;
    }

    public void searchProduct(String searchText) {
        inputText(searchField, searchText);
        // Wait for search process to be completed
        getActions().pause(Duration.ofSeconds(3)).perform();
    }

    public void clickProduct(String searchText) {
        clickTo(tableRowProductName(searchText));
    }

    public void clickTableRowActionBtn(String searchText) {
        clickTo(tableRowActionsBtn(searchText));
    }

    public boolean verifyPopupProductImages() {
        return isElementDisplay(popupProductImage, popupProductBarcodeImg, popupProductQrCodeImg);
    }

    /**
     * Verify product info in popup
     * @param element element to be verified
     * @param expectedValue expected value
     * @return true if actual value matches expected value
     */
    public boolean verifyPopupProductDetailInfo(WebElement element, String expectedValue) {
        String actualValue = getElementTextValueViaInnerText(element);
        return actualValue.equals(expectedValue);
    }

    public boolean verifyPopupProductType(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductType(), expectedValue);
    }

    public boolean verifyPopupProductName(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductName(), expectedValue);
    }

    public boolean verifyPopupProductCode(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductCode(), expectedValue);
    }

    public boolean verifyPopupProductCategory(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductCategory(), expectedValue);
    }

    public boolean verifyPopupProductUnit(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductUnit(), expectedValue);
    }

    public boolean verifyPopupProductCost(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductCost(), expectedValue);
    }

    public boolean verifyPopupProductPrice(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductPrice(), expectedValue);
    }

    public boolean verifyPopupProductTaxRate(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductTaxRate(), expectedValue);
    }

    public boolean verifyPopupProductTaxMethod(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductTaxMethod(), expectedValue);
    }

    public boolean verifyPopupProductAlertQuantity(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductAlertQuantity(), expectedValue);
    }

    public boolean verifyPopupProductDetails(String expectedValue) {
        return verifyPopupProductDetailInfo(popupProductDetails, expectedValue);
    }

    public EditProductPage clickEditProductBtn(String searchText) {
        clickTo(tableRowEditBtn(searchText));
        return new EditProductPage(getDriver());
    }
}