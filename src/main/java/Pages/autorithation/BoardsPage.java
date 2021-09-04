package Pages.autorithation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPage {



    @FindBy(xpath = "//button[@class=\"_2Hkk1T39xw4RMQ _3TTqkG5muwOzqZ ZOUktZwsING7-0\"]/p")
    private SelenideElement createButton;

    @FindBy(xpath = "//span[@class=\"_3jTRr1WChvvqHf _2VCqfvi0epFnIK\" and text()=\"Create board\"]")
    private SelenideElement createBoardButton;

    @FindBy(xpath = "//input[@placeholder=\"Add board title\"]")
    private SelenideElement addBoardTitleField;

    @FindBy(xpath = "//button[@data-test-id=\"create-board-submit-button\"]")
    private SelenideElement submitButton;

    @FindBy(xpath = "//span[@class=\"SETnvPbUKHW-cx\"]")
    private SelenideElement profillerButton;


    @FindBy(xpath = "//span[@class=\"mEyEHQTeZbhjil\"]")
    private SelenideElement userEmailField;

    public BoardsPage clickCreateButton(String title){
        createButton.click();
        createBoardButton.click();
        addBoardTitleField.setValue(title);
        submitButton.click();
        return this;
    }

    public BoardsPage clickProfillerButton(){
        profillerButton.click();
        return this;
    }

    public String getUserEmail() {
        return userEmailField.text();
    }

}
