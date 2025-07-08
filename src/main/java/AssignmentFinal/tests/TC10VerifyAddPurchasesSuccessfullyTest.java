package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.core.ExcelUtils;
import AssignmentFinal.core.RetryFailedTestCase;
import AssignmentFinal.pages.AddPurchaseListPage;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC10VerifyAddPurchasesSuccessfullyTest extends BaseTest {
    @Test(dataProvider = "TC10 Add Purchase Data",  retryAnalyzer = RetryFailedTestCase.class)
    public void verifyAddPurchasesSuccessfully(String date, String status, String warehouse, String supplier,
                                               String productName, String successMsg) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        AddPurchaseListPage addPurchaseListPage = dashboardPage.goToAddPurchaseListPage();

        addPurchaseListPage.inputDate(date);
        addPurchaseListPage.chooseWarehouseCombobox(warehouse);
        addPurchaseListPage.chooseStatusCombobox(status);
        addPurchaseListPage.chooseSupplier(supplier);
        addPurchaseListPage.chooseProduct(productName);
        addPurchaseListPage.clickSubmitBtn();
        Assert.assertTrue(dashboardPage.verifyAddSaleSuccessMsg(successMsg));
    }

    @DataProvider(name = "TC10 Add Purchase Data")
    public Object[][] dataProvider() {
        String filePath = "src/main/resources/LongNT148_AssignmentFinalExcelData.xlsx";
        return ExcelUtils.getTableArray(filePath, "TC10",
                1, 6);
    }
}
