package pages.pim;

import core.base.BasePage;
import core.context.ContextManager;
import core.context.TestContext;
import core.utils.JsonContext;
import org.openqa.selenium.By;

public class PimPage extends BasePage {
    private static final String EMPLOYEE_FIRST_NAME_KEY = "employeeFirstName";
    private static final String EMPLOYEE_LAST_NAME_KEY = "employeeLastName";

    private final By pimPageHeader = By.cssSelector("h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
    private final By addEmployeeButton = By.cssSelector("button[type='button'].oxd-button.oxd-button--medium.oxd-button--secondary");
    private final By firstNameInput = By.name("firstName");
    private final By middleNameInput = By.name("middleName");
    private final By lastNameInput = By.name("lastName");
    private final By employeeIdInput = By.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input");
    private final By imageUploadInput = By.xpath("//input[@type='file']");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By successMessage = By.xpath("//div[@id='oxd-toaster_1']//p[text()='Successfully Saved']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(pimPageHeader);
    }

    public void addEmployee(String employeeDataKey) {
        waitForPage();
        JsonContext.use("addUsers.json", employeeDataKey);

        String firstName = JsonContext.get("firstName");
        String middleName = JsonContext.get("middleName");
        String lastName = JsonContext.get("lastName");
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/testdata/images/" + JsonContext.get("image");

        TestContext context = ContextManager.get();
        context.put(EMPLOYEE_FIRST_NAME_KEY, firstName);
        context.put(EMPLOYEE_LAST_NAME_KEY, lastName);

        action.clickElement(addEmployeeButton);
        action.inputText(firstNameInput, firstName);
        action.inputText(middleNameInput, middleName);
        action.inputText(lastNameInput, lastName);
        action.inputText(employeeIdInput, action.generateRandomDigits(6));
        action.find(imageUploadInput).sendKeys(imagePath);
        action.clickElement(saveButton);
    }

    public boolean isEmployeeAddedSuccessfully() {
        return wait.isVisible(successMessage);
    }

    public boolean areEmployeeDetailsDisplayed() {
        String expectedFirstName = ContextManager.get().get(EMPLOYEE_FIRST_NAME_KEY);
        String expectedLastName = ContextManager.get().get(EMPLOYEE_LAST_NAME_KEY);

        wait.waitForVisibility(firstNameInput);
        String actualFirstName = action.find(firstNameInput).getAttribute("value");
        String actualLastName = action.find(lastNameInput).getAttribute("value");

        return expectedFirstName.equals(actualFirstName) && expectedLastName.equals(actualLastName);
    }
}
