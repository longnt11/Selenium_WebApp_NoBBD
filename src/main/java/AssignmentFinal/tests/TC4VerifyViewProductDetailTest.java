package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.core.ExcelUtils;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.ListProductsPage;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC4VerifyViewProductDetailTest extends BaseTest {
    @Test(dataProvider = "TC4 Popup Product Details Data")
    public void verifyViewProductDetail(String productType, String productName, String code, String category,
                                        String unit, String cost, String price, String taxRate, String taxMethod,
                                        String alertQuantity, String details) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        ListProductsPage listProductsPage = dashboardPage.goToListProductsPage();
        listProductsPage.searchProduct(productName);
        listProductsPage.clickProduct(productName);

        listProductsPage.verifyPopupProductImages();
        Assert.assertTrue(listProductsPage.verifyPopupProductImages());
        Assert.assertTrue(listProductsPage.verifyPopupProductType(productType));
        Assert.assertTrue(listProductsPage.verifyPopupProductName(productName));
        Assert.assertTrue(listProductsPage.verifyPopupProductCode(code));
        Assert.assertTrue(listProductsPage.verifyPopupProductCategory(category));
        Assert.assertTrue(listProductsPage.verifyPopupProductUnit(unit));
        Assert.assertTrue(listProductsPage.verifyPopupProductCost(cost));
        Assert.assertTrue(listProductsPage.verifyPopupProductPrice(price));
        Assert.assertTrue(listProductsPage.verifyPopupProductTaxRate(taxRate));
        Assert.assertTrue(listProductsPage.verifyPopupProductTaxMethod(taxMethod));
        Assert.assertTrue(listProductsPage.verifyPopupProductAlertQuantity(alertQuantity));
        Assert.assertTrue(listProductsPage.verifyPopupProductDetails(details));
    }

    @DataProvider(name = "TC4 Popup Product Details Data")
    public Object[][] dataProvider() {
        String filePath = "src/main/resources/LongNT148_AssignmentFinalExcelData.xlsx";
        return ExcelUtils.getTableArray(filePath, "TC4",
                1, 11);
    }
}
