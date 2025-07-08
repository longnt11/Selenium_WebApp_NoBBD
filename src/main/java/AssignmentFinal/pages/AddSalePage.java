package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddSalePage extends BasePage {
    public AddSalePage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(id = "sldate")
    private WebElement addSaleDate;

    @FindBy(id = "s2id_slbiller")
    private WebElement addSaleBillCombobox;

    @FindBy(id = "select2-results-2")
    private WebElement saleBillOptions;

    @FindBy(id = "s2id_slwarehouse")
    private WebElement addSaleWareHouseCombobox;

    @FindBy(id = "select2-results-3")
    private WebElement saleWareHouseOptions;

    @FindBy(id = "s2id_slcustomer")
    private WebElement addCustomerCombobox;

    @FindBy(id = "s2id_autogen12_search")
    private WebElement inputAddCustomerSearch;

    @FindBy(name = "add_item")
    private WebElement addProductsSearchBox;

    @FindBy(id = "s2id_slsale_status")
    private WebElement addSaleStatusCombobox;

    @FindBy(id = "select2-results-5")
    private WebElement saleStatusOptions;

    @FindBy(id = "s2id_slpayment_status")
    private WebElement addSalePaymentCombobox;

    @FindBy(id = "select2-results-6")
    private WebElement salePaymentOptions;

    @FindBy(id = "add_sale")
    private WebElement submitBtn;

    @FindBy(xpath = "//div[@id='payments']/div")
    private WebElement paymentStatusExpandableTab;

    // Functions
    public void inputDate(String date) {
        inputText(addSaleDate, date);
        addSaleDate.sendKeys(Keys.ESCAPE);
    }

    public void chooseBillCombobox(String billName) {
        chooseComboOption(addSaleBillCombobox, saleBillOptions, billName);
    }

    public void chooseWarehouseCombobox(String warehouseOption) {
        chooseComboOption(addSaleWareHouseCombobox, saleWareHouseOptions, warehouseOption);
    }

    public void chooseSaleStatusCombobox(String saleStatusOption) {
        chooseComboOption(addSaleStatusCombobox, saleStatusOptions, saleStatusOption);
    }

    public void chooseSalePaymentCombobox(String saleStatusOption) {
        chooseComboOption(addSalePaymentCombobox, salePaymentOptions, saleStatusOption);
        // Wait for payment status extra info tab to be expanded after Choosing Sale payment option
        getWait().until(ExpectedConditions.visibilityOf(paymentStatusExpandableTab));
    }

    public void chooseCustomer(String customerOption) {
        chooseComboOptionViaSearch(addCustomerCombobox, inputAddCustomerSearch, customerOption);
    }

    public void chooseProduct(String productOption) {
        chooseComboOptionViaSearch(addProductsSearchBox, addProductsSearchBox, productOption);
    }

    public void clickSubmitBtn() {
        scrollToFooter();
        clickTo(submitBtn);
    }
}