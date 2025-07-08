package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.ListPurchasesPage;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC9VerifyPurchasesListTest extends BaseTest {
    @Test
    public void testVerifyPurchasesList() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        ListPurchasesPage listPurchasesPage = dashboardPage.goToPurchaseListPage();
        Assert.assertTrue(listPurchasesPage.verifyDateFormat());
        Assert.assertTrue(listPurchasesPage.verifyReferenceNoDescending());
        // This assertion may result in failure because the number of rows are likely to be added by other users
        Assert.assertTrue(listPurchasesPage.verifyNumberOfTableRows(10));
    }
}
