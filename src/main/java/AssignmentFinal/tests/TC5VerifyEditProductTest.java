package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.core.ExcelUtils;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.EditProductPage;
import AssignmentFinal.pages.ListProductsPage;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC5VerifyEditProductTest extends BaseTest {
    @Test(dataProvider = "TC5 Product Edit Data")
    public void verifyEditProduct(String productName,String productType, String addProductName,String barcodeSymbology,String taxMethod,
                                  String failMsg) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        ListProductsPage listProductsPage = dashboardPage.goToListProductsPage();

        listProductsPage.searchProduct(productName);
        listProductsPage.clickTableRowActionBtn(productName);
        EditProductPage editProductPage = listProductsPage.clickEditProductBtn(productName);

        Assert.assertTrue(editProductPage.isPageDisplay());

        editProductPage.selectProductType(productType);
        editProductPage.addProduct(addProductName);
        editProductPage.selectBarcodeSymbologyComboBox(barcodeSymbology);
        editProductPage.selectTaxMethod(taxMethod);
        editProductPage.clickEditProductButton();

        Assert.assertTrue(editProductPage.verifyFailMessage(failMsg));
    }

    @DataProvider(name = "TC5 Product Edit Data")
    public Object[][] dataProvider() {
        String filePath = "src/main/resources/LongNT148_AssignmentFinalExcelData.xlsx";
        return ExcelUtils.getTableArray(filePath, "TC5",
                1, 6);
    }
}