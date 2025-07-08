package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(id = "ov-chart")
    private WebElement overviewChart;

    @FindBy(xpath = "//h2[normalize-space()='Quick Links']//parent::div//following-sibling::div//div[not(@class='clearfix')]")
    private List<WebElement> quickLinksList;

    @FindBy(xpath = "//h2[normalize-space()='Latest Five']//parent::div//following-sibling::div//ul[@id='dbTab']//parent::div")
    private List<WebElement> latestFiveTableList;

    @FindBy(xpath = "//h2[contains(text(),'Best Sellers')]//parent::div//following-sibling::div//div[@id='bschart']")
    private List<WebElement> bestSellersChartList;

    /**
     * Find Left Menu Link locator by using dynamic xpath
     *
     * @param leftMenuLinkText text of left menu link
     * @return WebElement of Left menu link
     */
    private WebElement leftMenuLink(String leftMenuLinkText) {
        String dynamicXpath = "//div[@id='sidebar-left']//a[normalize-space()='%s']";
        return findElementByDynamicXpath(dynamicXpath, leftMenuLinkText);
    }

    private WebElement productsMenu() {
        return leftMenuLink("Products");
    }

    private WebElement listProductsMenu() {
        return leftMenuLink("List Products");
    }

    private WebElement printBarcodeLabelMenu() {
        return leftMenuLink("Print Barcode/Label");
    }

    private WebElement salesMenu() {
        return leftMenuLink("Sales");
    }

    private WebElement addSalesMenu() {
        return leftMenuLink("Add Sale");
    }

    private WebElement purchasesMenu() {
        return leftMenuLink("Purchases");
    }

    private WebElement listPurchasesMenu() {
        return leftMenuLink("List Purchases");
    }

    private WebElement addPurchasesMenu() {
        return leftMenuLink("Add Purchase");
    }

    // Functions
    public boolean verifyLoginSuccessMsg(String successMsg) {
        return verifySuccessMsg(successMsg);
    }

    public boolean verifyAddSaleSuccessMsg(String successMsg) {
        return verifySuccessMsg(successMsg);
    }

    public boolean isPageDisplay() {
        return isElementDisplay(successMessage, overviewChart, overviewChart) &&
                isElementDisplay(quickLinksList) &&
                isElementDisplay(latestFiveTableList) &&
                isElementDisplay(bestSellersChartList);
    }

    public ListProductsPage goToListProductsPage() {
        clickTo(productsMenu());
        clickTo(listProductsMenu());
        return new ListProductsPage(getDriver());
    }

    public PrintBarcodeLabelPage goToPrintBarcodeLabelPage() {
        clickTo(productsMenu());
        clickTo(printBarcodeLabelMenu());
        return new PrintBarcodeLabelPage(getDriver());
    }

    public AddSalePage goToAddSalePage() {
        clickTo(salesMenu());
        clickTo(addSalesMenu());
        return new AddSalePage(getDriver());
    }

    public ListPurchasesPage goToPurchaseListPage() {
        clickTo(purchasesMenu());
        clickTo(listPurchasesMenu());
        return new ListPurchasesPage(getDriver());
    }

    public AddPurchaseListPage goToAddPurchaseListPage() {
        clickTo(purchasesMenu());
        clickTo(addPurchasesMenu());
        return new AddPurchaseListPage(getDriver());
    }
}
