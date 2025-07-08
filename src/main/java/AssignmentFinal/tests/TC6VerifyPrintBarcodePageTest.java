package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.LoginPage;
import AssignmentFinal.pages.PrintBarcodeLabelPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC6VerifyPrintBarcodePageTest extends BaseTest {
    @Test
    public void verifyPrintBarcode() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        PrintBarcodeLabelPage printBarcodeLabelPage = dashboardPage.goToPrintBarcodeLabelPage();
        Assert.assertTrue(printBarcodeLabelPage.isPageDisplay());
    }
}
