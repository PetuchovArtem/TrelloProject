package Pages.autorithation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class InputLoginPage {

    public final String EMAIL="petuchov.artem@gmail.com";
    public final String PASS="dispersia13";

    @FindBy(xpath = "//input[@ placeholder=\"Enter email\"]")
    private SelenideElement inputLoginField;

    @FindBy(xpath = "//input[@ class=\"button account-button button-green btn btn-success\"]")
    private SelenideElement loginWithAtlassianButton;

    @FindBy(xpath = "//input[@ aria-labelledby=\"password-uid1-label password-uid1-helper password-uid1-valid password-uid1-error\"]")
    private SelenideElement inputPasswordField;

    @FindBy(xpath = "//button[@id=\"login-submit\"]")
    private SelenideElement loginButton;

        public BoardsPage setLoginField()  {
        inputLoginField.setValue(EMAIL);
        loginWithAtlassianButton.click();
        inputPasswordField.setValue(PASS);
        loginButton.click();
        return page(BoardsPage.class);
    }


}
