package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProductPage extends BasePage {
    public EditProductPage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(name = "name")
    private WebElement nameTextInputField;

    @FindBy(name = "code")
    private WebElement codeTextInputField;

    @FindBy(name = "slug")
    private WebElement slugTextInputField;

    @FindBy(xpath = "//ul[@id='select2-results-6']//preceding-sibling::div//input")
    private WebElement barcodeSymbologySearchField;

    @FindBy(name = "edit_product")
    private WebElement editProductButton;

    @FindBy(name = "add_item")
    private WebElement addProductTextField;

    private WebElement findComboboxField(String fieldName) {
        String dynamicXpath = "//label[normalize-space()='%s']//preceding-sibling::a";
        return findElementByDynamicXpath(dynamicXpath, fieldName);
    }

    private WebElement productTypeCombobox() {
        return findComboboxField("Product Type");
    }

    private WebElement barcodeSymbologyComboBox() {
        return findComboboxField("Barcode Symbology");
    }

    private WebElement taxMethodComboBox() {
        return findComboboxField("Tax Method");
    }

    private WebElement productTypeOption(String optionName) {
        String dynamicXpath = "//ul[@id='select2-results-5']//li[normalize-space()='%s']";
        return findElementByDynamicXpath(dynamicXpath, optionName);
    }

    private WebElement taxMethodOption(String optionName) {
        String dynamicXpath = "//ul[@id='select2-results-13']//li[normalize-space()='%s']";
        return findElementByDynamicXpath(dynamicXpath, optionName);
    }

    // Functions
    public void selectProductType(String productType) {
        clickTo(productTypeCombobox());
        clickTo(productTypeOption(productType));
    }

    public void selectTaxMethod(String TaxMethodOption) {
        clickTo(taxMethodComboBox());
        clickTo(taxMethodOption(TaxMethodOption));
    }

    public void selectBarcodeSymbologyComboBox(String barcodeSymbologyOption) {
        clickTo(barcodeSymbologyComboBox());
        inputText(barcodeSymbologySearchField, barcodeSymbologyOption);
        barcodeSymbologySearchField.sendKeys(Keys.ENTER);
    }

    public void addProduct(String productName) {
        clickTo(addProductTextField);
        inputText(addProductTextField, productName);
    }

    public void clickEditProductButton() {
        editProductButton.sendKeys(Keys.ENTER);
    }

    public boolean isPageDisplay() {
        return isElementDisplay(nameTextInputField, codeTextInputField, codeTextInputField,
                productTypeCombobox(), barcodeSymbologyComboBox());
    }

    public boolean verifyFailMessage(String errorMsg) {
        return verifyFailMsg(errorMsg);
    }
}
