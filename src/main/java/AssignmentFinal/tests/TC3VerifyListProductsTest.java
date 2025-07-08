package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.core.ExcelUtils;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.ListProductsPage;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC3VerifyListProductsTest extends BaseTest {
    @Test(dataProvider = "TC3 Table column name data")
    public void verifyListProducts(String column1, String column2, String column3, String column4, String column5,
                                   String column6, String column7, String column8, String column9, String column10) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        ListProductsPage listProductsPage = dashboardPage.goToListProductsPage();
        Assert.assertTrue(listProductsPage.verifyTableColumnName(
                column1, column2, column3, column4, column5,
                column6, column7, column8, column9, column10));
    }

    @DataProvider(name = "TC3 Table column name data")
    public Object[][] dataProvider() {
        String filePath = "src/main/resources/LongNT148_AssignmentFinalExcelData.xlsx";
        return ExcelUtils.getTableArray(filePath, "TC3",
                1, 10);
    }
}
