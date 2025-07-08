package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC1VerifyLoginPageTest extends BaseTest {
    @Test
    public void verifyLoginPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());

        Assert.assertTrue(loginPage.isPageDisplay()); // All elements to be tested in is method
    }
}
