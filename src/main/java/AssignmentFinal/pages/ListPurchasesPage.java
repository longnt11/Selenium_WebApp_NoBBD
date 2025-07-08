package AssignmentFinal.pages;

import AssignmentFinal.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListPurchasesPage extends BasePage {
    public ListPurchasesPage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(xpath = "//table[@id='POData']//tbody//tr")
    private List<WebElement> purchasesTableRow;

    @FindBy(xpath = "//th[normalize-space()='Reference No']")
    private WebElement referenceNoColumnName;

    // Functions
    public boolean verifyDateFormat() {
        getWait().until(ExpectedConditions.visibilityOfAllElements(purchasesTableRow));
        getActions().pause(Duration.ofSeconds(3)).perform();

        boolean flag = false;
        // Date format in regex
        String regex = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        // Loop for each row to get cell containing date
        for (WebElement row : purchasesTableRow) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                // If any cell matches the regex, function will return true and break the loop to search bor the next row
                Matcher matcher = pattern.matcher(cell.getText());
                if (matcher.matches()) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * Verify if Reference No column are sorted descending by default
     * @return true if correct and vice versa
     */
    public boolean verifyReferenceNoDescending() {
        return referenceNoColumnName.getAttribute("className").equals("sorting_desc");
    }

    public boolean verifyNumberOfTableRows(int expectedRows) {
        return purchasesTableRow.size() == expectedRows;
    }
}