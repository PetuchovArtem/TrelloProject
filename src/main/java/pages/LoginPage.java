package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {


    @FindBy(xpath = "//a[@class=\"btn btn-sm btn-link text-primary\"]")
    private SelenideElement loginButton;

    public InputLoginPage clickLoginButton() {
        loginButton.click();
        return page(InputLoginPage.class);
    }


}
