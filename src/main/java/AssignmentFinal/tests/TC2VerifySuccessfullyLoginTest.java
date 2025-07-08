package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.core.ExcelUtils;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC2VerifySuccessfullyLoginTest extends BaseTest {
    @Test(dataProvider = "TC2 Login Credential")
    public void verifySuccessfullyLogin(String username, String password, String successMsg) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(username, password);

        Assert.assertTrue(dashboardPage.verifyLoginSuccessMsg(successMsg));
        Assert.assertTrue(dashboardPage.isPageDisplay()); // All elements to be tested in is method
    }

    @DataProvider(name = "TC2 Login Credential")
    public Object[][] dataProviderLogin() {
        String filePath = "src/main/resources/LongNT148_AssignmentFinalExcelData.xlsx";
        return ExcelUtils.getTableArray(filePath, "TC2", 1, 3);
    }
}
