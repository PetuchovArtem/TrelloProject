package Pages.autorithation;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.webdriver;

public class BoardsPage {




    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div[1]/button/p")
    private SelenideElement createButton;

    @FindBy(xpath = "//div[@title=\"name1\"]")
    private SelenideElement currentBoardTitle;


    @FindBy(xpath = "//span[@class=\"_3jTRr1WChvvqHf _2VCqfvi0epFnIK\" and text()=\"Create board\"]")
    private SelenideElement createBoardButton;

    @FindBy(xpath = "//input[@placeholder=\"Add board title\"]")
    private SelenideElement addBoardTitleField;

    @FindBy(xpath = "//button[@data-test-id=\"create-board-submit-button\"]")
    private SelenideElement submitButton;

    @FindBy(xpath = "//span[@class=\"SETnvPbUKHW-cx\"]")
    private SelenideElement profillerButton;

    @FindBy(xpath ="//button[@data-test-id=\"header-create-menu-button\"]")
    private SelenideElement createButtonHeader;


    @FindBy(xpath = "//span[@class=\"mEyEHQTeZbhjil\"]")
    private SelenideElement userEmailField;






    public CurrentBoardPage createNewBoard(String title){
        createButton.click();
        createBoardButton.click();
        addBoardTitleField.setValue(title);
        submitButton.click();
        return page(CurrentBoardPage.class);
    }

    public String createXpath(String title){
        String xpathtitle = "//div[@title=\""+title+"\"]";
        return xpathtitle;
    }


    public CurrentBoardPage openCurrentBoard(String title){
        currentBoardTitle.click();
        return page(CurrentBoardPage.class);
    }


    public BoardsPage clickProfillerButton(){
        profillerButton.click();
        return this;
    }

    public String getUserEmail() {
          return userEmailField.text();
    }


}
