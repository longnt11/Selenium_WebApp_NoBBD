package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddPurchaseListPage extends BasePage {
    public AddPurchaseListPage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(id = "podate")
    private WebElement addDate;

    @FindBy(id = "s2id_postatus")
    private WebElement addStatusCombobox;

    @FindBy(id = "select2-results-2")
    private WebElement statusOptions;

    @FindBy(id = "s2id_powarehouse")
    private WebElement addWareHouseCombobox;

    @FindBy(id = "select2-results-1")
    private WebElement saleWareHouseOptions;

    @FindBy(id = "s2id_posupplier")
    private WebElement addSupplierCombobox;

    @FindBy(id = "s2id_autogen8_search")
    private WebElement inputAddSupplierSearch;

    @FindBy(name = "add_item")
    private WebElement addProductsSearchBox;

    @FindBy(id = "add_pruchase")
    private WebElement submitBtn;

    // Functions
    public void inputDate(String date) {
        inputText(addDate, date);
        addDate.sendKeys(Keys.ESCAPE);
    }

    public void chooseStatusCombobox(String statusName) {
        chooseComboOption(addStatusCombobox, statusOptions, statusName);
    }

    public void chooseWarehouseCombobox(String warehouseOption) {
        chooseComboOption(addWareHouseCombobox, saleWareHouseOptions, warehouseOption);
    }

    public void chooseSupplier(String supplierOption) {
        chooseComboOptionViaSearch(addSupplierCombobox, inputAddSupplierSearch, supplierOption);
    }

    public void chooseProduct(String productOption) {
        chooseComboOptionViaSearch(addProductsSearchBox, addProductsSearchBox, productOption);
    }

    public void clickSubmitBtn() {
        scrollToFooter();
        clickTo(submitBtn);
    }
}