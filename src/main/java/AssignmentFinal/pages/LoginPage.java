package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "identity")
    private WebElement usernameTextInput;

    @FindBy(name = "password")
    private WebElement passwordTextInput;

    @FindBy(xpath = "//label[@for='remember']")
    private WebElement rememberMeCheckBox;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;

    public void navigateToLoginPage(String url) {
        getDriver().navigate().to(url);
    }

    public boolean isPageDisplay() {
        return isElementDisplay(usernameTextInput, passwordTextInput, rememberMeCheckBox, loginButton);
    }

    public DashboardPage login(String username, String password) {
        inputText(usernameTextInput, username);
        inputText(passwordTextInput, password);
        clickTo(loginButton);
        return new DashboardPage(getDriver());
    }
}
