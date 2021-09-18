package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class DeleteBoardPage {

    @FindBy(xpath = "//a[@class=\"quiet js-delete\"]")
    private SelenideElement finalyDeleteBoardLink;


    @FindBy(xpath = "//input[@class=\"js-confirm full nch-button nch-button--danger\"]")
    private SelenideElement finalyDeleteBoardButton;


    @FindBy(xpath = "//h1")
    private SelenideElement checkDelite;

    public DeleteBoardPage deleteBoard() {
        finalyDeleteBoardLink.click();
        finalyDeleteBoardButton.click();
        return this;
    }

    public String checkDeleteBoard() {
        return checkDelite.getText();
    }

}
