package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.core.ExcelUtils;
import AssignmentFinal.pages.AddSalePage;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC8VerifyAddSaleSuccessfullyTest extends BaseTest {
    @Test(dataProvider = "TC8 Add Sale Data")
    public void testAddSaleSuccessfully(String date, String bill, String warehouse, String customer,
                                        String productName,String saleStatus, String salePayment, String successMsg) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        AddSalePage addSalePage = dashboardPage.goToAddSalePage();
        addSalePage.inputDate(date);
        addSalePage.chooseBillCombobox(bill);
        addSalePage.chooseWarehouseCombobox(warehouse);
        addSalePage.chooseCustomer(customer);
        addSalePage.chooseProduct(productName);
        addSalePage.chooseSaleStatusCombobox(saleStatus);
        addSalePage.chooseSalePaymentCombobox(salePayment);
        addSalePage.clickSubmitBtn();
        Assert.assertTrue(dashboardPage.verifyAddSaleSuccessMsg(successMsg));
    }

    @DataProvider(name = "TC8 Add Sale Data")
    public Object[][] dataProvider() {
        String filePath = "src/main/resources/LongNT148_AssignmentFinalExcelData.xlsx";
        return ExcelUtils.getTableArray(filePath, "TC8",
                1, 8);
    }
}
