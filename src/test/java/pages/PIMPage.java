package pages;

import org.openqa.selenium.By;

import core.base.BasePage;
import core.context.ContextManager;
import core.context.TestContext;
import core.utils.JsonContext;

public class PIMPage extends BasePage {
    private final By PIM_PAGE_HEADER = By.cssSelector("h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
    private final By ADD_USER_BUTTON = By
            .cssSelector("button[type='button'].oxd-button.oxd-button--medium.oxd-button--secondary");

    private final By PIM_Button = By.xpath("//a[contains(normalize-space(),'PIM')]");
    private final By firstName_input = By.name("firstName");
    private final By middleName_input = By.name("middleName");
    private final By lastName_input = By.name("lastName");
    private final By employee_ID_input = By
            .xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input");
    private final By uploadInput = By.xpath("//input[@type='file']");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By SUCCESS_MESSAGE = By.xpath("//div[@id='oxd-toaster_1']//p[text()='Successfully Saved']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(PIM_PAGE_HEADER);
    }

    public void addUser(String scenarioNode) {
        waitForPage();
        JsonContext.use("addUsers.json", scenarioNode);
        String firstName = JsonContext.get("firstName");
        String middleName = JsonContext.get("middleName");
        String lastName = JsonContext.get("lastName");
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/testdata/images/"
                + JsonContext.get("image");
        TestContext ctx = ContextManager.get();
        ctx.put("employeeFirstName", firstName);
        ctx.put("employeeLastName", lastName);
        action.clickElement(PIM_Button);
        action.clickElement(ADD_USER_BUTTON);
        action.inputText(firstName_input, firstName);
        action.inputText(middleName_input, middleName);
        action.inputText(lastName_input, lastName);
        action.inputText(employee_ID_input, action.generateRandomDigits(9));
        action.find(uploadInput).sendKeys(imagePath);
        action.clickElement(submitButton);
    }

    public boolean isEmployeeAddedSuccessfully() {
        return wait.isVisible(SUCCESS_MESSAGE);
    }

    public boolean areEmployeeDetailsDisplayed() {
        TestContext ctx = ContextManager.get();
        String expectedFirstName = ctx.get("employeeFirstName");
        String expectedLastName = ctx.get("employeeLastName");
        wait.waitForVisibility(firstName_input);
        String actualFirstName = action.find(firstName_input).getAttribute("value");
        String actualLastName = action.find(lastName_input).getAttribute("value");
        return expectedFirstName.equals(actualFirstName) && expectedLastName.equals(actualLastName);
    }
}
