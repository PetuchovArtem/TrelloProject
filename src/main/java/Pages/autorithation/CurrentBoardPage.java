package Pages.autorithation;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.webdriver;

public class CurrentBoardPage {


    @FindBy(xpath = "//h1")
    private SelenideElement pageName;


    @FindBy(xpath = "//a[@class=\"open-add-list js-open-add-list\"]")
    private SelenideElement addAList;

    @FindBy(xpath = "//div[@class=\"js-add-list list-wrapper mod-add is-idle\"]")
    private SelenideElement addAnotherList;


    @FindBy(xpath = "//input[@class=\"list-name-input\"]")
    private SelenideElement inputListNameField;

    @FindBy(xpath = "//input[@class=\"nch-button nch-button--primary mod-list-add-button js-save-edit\"]")
    private SelenideElement inputListNameAddButton;

    @FindBy(xpath = "//a[@class=\"open-card-composer js-open-card-composer\"]")
    private SelenideElement addACard;

    @FindBy(xpath = "//textarea[@class=\"list-card-composer-textarea js-card-title\"]")
    private SelenideElement inputCardNameField;

    @FindBy(xpath = "//input[@class=\"nch-button nch-button--primary confirm mod-compact js-add-card\"]")
    private SelenideElement inputCardNameAddButton;

    @FindBy(xpath = "//textarea[@class=\"list-header-name mod-list-name js-list-name-input\"]")
    private SelenideElement currentListName;

    @FindBy(xpath = "//span[@class=\"list-card-title js-card-name\"]")
    private SelenideElement currentCardName;

    @FindBy(xpath = "//div[@class=\"list-card-details js-card-details\"]")
    private SelenideElement currentCard;

    @FindBy(xpath = "//*[@id=\"board\"]/div[2]/div")
    private SelenideElement secondList;

    @FindBy(xpath = "//span[@class=\"list-card-title js-card-name\"]")
    private SelenideElement getNameCardOnSecondList;





    public String getPageName() {
        return pageName.getText();
    }

    public CurrentBoardPage addAList(String title) {
        addAList.click();
        inputListNameField.setValue(title);
        inputListNameAddButton.click();
        return this;
    }

    public CurrentBoardPage addAnotherList(String title) {
        addAnotherList.click();
        inputListNameField.setValue(title);
        inputListNameAddButton.click();
        return this;
    }



    public CurrentBoardPage addACard(String title) {
        addACard.click();
        inputCardNameField.setValue(title);
        inputCardNameAddButton.click();
        return this;
    }

    public CurrentBoardPage moveCard() {

        WebElement cardForMove = currentCard;
        WebElement placeToMove = secondList;

        new Actions(webdriver().object())
                .dragAndDrop(cardForMove,placeToMove)
                .release()
                .perform();
        return this;
    }


    public String getListName() {
        return currentListName.getText();
    }

    public String getCardName() {
        return currentCardName.getText();
    }

    public String getCardNameOnSecondList() {
        return getNameCardOnSecondList.getText();
    }


}
