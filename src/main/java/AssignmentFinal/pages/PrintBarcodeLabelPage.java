package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrintBarcodeLabelPage extends BasePage {
    public PrintBarcodeLabelPage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(name = "add_item")
    private WebElement addProductField;

    @FindBy(id = "select2-chosen-1")
    private WebElement styleField;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement styleFieldSearchBox;

    @FindBy(name = "print")
    private WebElement updateBtn;

    private WebElement findPrintCheckbox(String checkboxId) {
        String dynamicXpath = "//input[@id='%s']//parent::div";
        return findElementByDynamicXpath(dynamicXpath, checkboxId);
    }

    private WebElement siteNameCheckbox() {
        return findPrintCheckbox("site_name");
    }

    private WebElement productNameCheckbox() {
        return findPrintCheckbox("product_name");
    }

    private WebElement productPriceCheckbox() {
        return findPrintCheckbox("price");
    }

    private WebElement currenciesCheckbox() {
        return findPrintCheckbox("currencies");
    }

    private WebElement unitCheckbox() {
        return findPrintCheckbox("unit");
    }

    private WebElement categoryCheckbox() {
        return findPrintCheckbox("category");
    }

    private WebElement variantsCheckbox() {
        return findPrintCheckbox("variants");
    }

    private WebElement productImageCheckbox() {
        return findPrintCheckbox("product_image");
    }

    private WebElement checkPromoCheckbox() {
        return findPrintCheckbox("check_promo");
    }

    private WebElement barcodeInfo(String barcodeInfoClassName) {
        String dynamicXpath = "//span[contains(@class,'%s')]";
        return findElementByDynamicXpath(dynamicXpath, barcodeInfoClassName);
    }

    private WebElement barcodeProductImage() {
        return barcodeInfo("product_image").findElement(By.tagName("img"));
    }

    private WebElement barcodeSite() {
        return barcodeInfo("barcode_site");
    }

    private WebElement barcodeName() {
        return barcodeInfo("barcode_name");
    }

    private WebElement barcodePrice() {
        return barcodeInfo("barcode_price");
    }

    private WebElement barcodeUnit() {
        return barcodeInfo("barcode_unit");
    }

    private WebElement barcodeCategory() {
        return barcodeInfo("barcode_category");
    }

    private WebElement barcodeImage() {
        return barcodeInfo("barcode_image").findElement(By.tagName("img"));
    }

    // Functions
    public void searchProduct(String productName) {
        inputText(addProductField, productName);
        addProductField.sendKeys(Keys.ENTER);
    }

    public void searchStyle(String styleName) {
        clickTo(styleField);
        inputText(styleFieldSearchBox, styleName);
        styleFieldSearchBox.sendKeys(Keys.ENTER);
    }

    /**
     * Select or unselect checkbox by passing yes or no
     *
     * @param element checkbox element
     * @param flag    yes to select checkbox and no to unselect checkbox
     */
    public void selectCheckbox(WebElement element, String flag) {
        if (flag.equals("yes")) {
            checkCheckbox(element);
        } else if (flag.equals("no")) {
            uncheckCheckbox(element);
        }
    }

    public void selectSiteNameCheckbox(String flag) {
        selectCheckbox(siteNameCheckbox(), flag);
    }

    public void selectProductNameCheckbox(String flag) {
        selectCheckbox(productNameCheckbox(), flag);
    }

    public void selectProductPriceCheckbox(String flag) {
        selectCheckbox(productPriceCheckbox(), flag);
    }

    public void selectCurrencyCheckbox(String flag) {
        selectCheckbox(currenciesCheckbox(), flag);
    }

    public void selectUnitCheckbox(String flag) {
        selectCheckbox(unitCheckbox(), flag);
    }

    public void selectCategoryCheckbox(String flag) {
        selectCheckbox(categoryCheckbox(), flag);
    }

    public void selectVariantsCheckbox(String flag) {
        selectCheckbox(variantsCheckbox(), flag);
    }

    public void selectImageCheckbox(String flag) {
        selectCheckbox(productImageCheckbox(), flag);
    }

    public void selectCheckPromoCheckbox(String flag) {
        selectCheckbox(checkPromoCheckbox(), flag);
    }

    public void clickUpdateBtn() {
        clickTo(updateBtn);
    }

    public boolean verifyBarcodeProductImage(String productImageUrl) {
        return barcodeProductImage().getAttribute("src").contains(productImageUrl);
    }

    public boolean verifyBarcodeSiteName(String siteName) {
        return getElementTextValueViaInnerText(barcodeSite()).contains(siteName);
    }

    public boolean verifyBarcodeName(String barcodeName) {
        return getElementTextValueViaInnerText(barcodeName()).contains(barcodeName);
    }

    public boolean verifyBarcodePrice(String barcodePrice) {
        return getElementTextValueViaInnerText(barcodePrice()).contains(barcodePrice);
    }

    public boolean verifyBarcodeUnit(String barcodeUnit) {
        return getElementTextValueViaInnerText(barcodeUnit()).contains(barcodeUnit);
    }

    public boolean verifyBarcodeCategory(String barcodeCategory) {
        return getElementTextValueViaInnerText(barcodeCategory()).contains(barcodeCategory);
    }

    public boolean verifyBarcodeImage(String barcodeName) {
        return barcodeImage().getAttribute("src").contains(barcodeName);
    }

    public void checkCheckbox(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        String elementClassAttribute = element.getAttribute("className");
        if (!elementClassAttribute.contains("checked")) {
            element.click();
        }
    }

    public void uncheckCheckbox(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        String elementClassAttribute = element.getAttribute("className");
        if (elementClassAttribute.contains("checked")) {
            element.click();
        }
    }

    public boolean isPageDisplay() {
        return isElementDisplay(addProductField, styleField, siteNameCheckbox(), productNameCheckbox(),
                productPriceCheckbox(), currenciesCheckbox(), unitCheckbox(), categoryCheckbox(),
                variantsCheckbox(), productImageCheckbox(), checkPromoCheckbox());
    }
}