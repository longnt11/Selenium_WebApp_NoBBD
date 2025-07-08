package AssignmentFinal.tests;

import AssignmentFinal.core.BaseTest;
import AssignmentFinal.core.ExcelUtils;
import AssignmentFinal.pages.DashboardPage;
import AssignmentFinal.pages.LoginPage;
import AssignmentFinal.pages.PrintBarcodeLabelPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC7VerifyCreatePrintBarcodeLabelTest extends BaseTest {
    @Test(dataProvider = "TC7 Print barcode label Data")
    public void testCreatePrintBarcodeLabel(String productName, String style, String isSiteNameSelect,
                                            String isProductNameSelect, String isPriceSelect, String isCurrencySelect,
                                            String isUnitSelect, String isCategorySelect, String isVariantSelect,
                                            String isImageSelect, String isCheckPromoSelect, String barcodeSite,
                                            String barcodeName, String barcodePrice, String barcodeUnit,
                                            String barcodeCategory, String barcodeImage, String productImage) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(getLoginUrl());
        Assert.assertTrue(loginPage.isPageDisplay());
        DashboardPage dashboardPage = loginPage.login(getUsername(), getPassword());

        PrintBarcodeLabelPage printBarcodeLabelPage = dashboardPage.goToPrintBarcodeLabelPage();
        Assert.assertTrue(printBarcodeLabelPage.isPageDisplay());

        printBarcodeLabelPage.searchProduct(productName);
        printBarcodeLabelPage.searchStyle(style);
        printBarcodeLabelPage.selectSiteNameCheckbox(isSiteNameSelect);
        printBarcodeLabelPage.selectProductNameCheckbox(isProductNameSelect);
        printBarcodeLabelPage.selectProductPriceCheckbox(isPriceSelect);
        printBarcodeLabelPage.selectCurrencyCheckbox(isCurrencySelect);
        printBarcodeLabelPage.selectUnitCheckbox(isUnitSelect);
        printBarcodeLabelPage.selectCategoryCheckbox(isCategorySelect);
        printBarcodeLabelPage.selectVariantsCheckbox(isVariantSelect);
        printBarcodeLabelPage.selectImageCheckbox(isImageSelect);
        printBarcodeLabelPage.selectCheckPromoCheckbox(isCheckPromoSelect);
        printBarcodeLabelPage.clickUpdateBtn();

        String siteNameUpperCase = barcodeSite.toUpperCase();
        String nameUpperCase = barcodeName.toUpperCase();
        String priceUpperCase = barcodePrice.toUpperCase();
        String unitUpperCase = barcodeUnit.toUpperCase();
        String categoryUpperCase = barcodeCategory.toUpperCase();

        Assert.assertTrue(printBarcodeLabelPage.verifyBarcodeProductImage(productImage));
        Assert.assertTrue(printBarcodeLabelPage.verifyBarcodeSiteName(siteNameUpperCase));
        Assert.assertTrue(printBarcodeLabelPage.verifyBarcodeName(nameUpperCase));
        Assert.assertTrue(printBarcodeLabelPage.verifyBarcodePrice(priceUpperCase));
        Assert.assertTrue(printBarcodeLabelPage.verifyBarcodeUnit(unitUpperCase));
        Assert.assertTrue(printBarcodeLabelPage.verifyBarcodeCategory(categoryUpperCase));
        Assert.assertTrue(printBarcodeLabelPage.verifyBarcodeImage(barcodeImage));
    }

    @DataProvider(name = "TC7 Print barcode label Data")
    public Object[][] dataProvider() {
        String filePath = "src/main/resources/LongNT148_AssignmentFinalExcelData.xlsx";
        return ExcelUtils.getTableArray(filePath, "TC7",
                1, 18);
    }
}
