package tests;

import Pages.autorithation.BoardsPage;
import Pages.autorithation.CurrentBoardPage;
import Pages.autorithation.InputLoginPage;
import Pages.autorithation.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.*;

public class TrelloTest {

    public LoginPage mainPage;
    public String newBoardName = "name1";
    public String newListName = "newListName";
    public String newCardName = "newCardName";

    @BeforeAll
    public static void setUp() {

        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void Autorization(){
        mainPage = open("https://trello.com/", LoginPage.class);
    }


    @Test
    public void testCreateNewBoard() {
        CurrentBoardPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().createNewBoard(newBoardName);

        String actualPageName = inputLoginPage.getPageName();
        Assertions.assertEquals(newBoardName, actualPageName);
    }

    @Test
    public void testCreateNewListWithCard(){
        CurrentBoardPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);
        inputLoginPage.addAList("newListName").addACard("newCardName");

        String actualListName = inputLoginPage.getListName();
        String actualCardName = inputLoginPage.getCardName();

        Assertions.assertEquals(newListName, actualListName);
        Assertions.assertEquals(newCardName, actualCardName);

    }

    @Test
    public void testMoveCard(){
        CurrentBoardPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);
        inputLoginPage.moveCard();
        Assertions.assertEquals(newCardName, inputLoginPage.getCardNameOnSecondList());
    }


}
