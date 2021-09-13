package tests;

import Pages.autorithation.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloTest {

    public LoginPage mainPage;
    public String newBoardName = "name12";
    public String newListName = "newListName1";
    public String newCardName = "newCardName1";
    public String secondListName = "SecondList";

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void Autorization() {
        mainPage = open("https://trello.com/", LoginPage.class);
    }

    @AfterEach
    public void clouseBrowser(){
        closeWebDriver();
    }

    @Test
    @Order(1)
    public void testCreateNewBoard() {
        CurrentBoardPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().createNewBoard(newBoardName);

        String actualPageName = inputLoginPage.getPageName();
        Assertions.assertEquals(newBoardName, actualPageName);
    }

    @Test
    @Order(2)
    public void testCreateNewListWithCard() {
        CurrentBoardPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);
        inputLoginPage.addAList(newListName).addACard(newCardName);

        String actualListName = inputLoginPage.getListName();
        String actualCardName = inputLoginPage.getCardName();

        Assertions.assertEquals(newListName, actualListName);
        Assertions.assertEquals(newCardName, actualCardName);
    }

    @Test
    @Order(3)
    public void testMoveCard() {
        CurrentBoardPage boardpage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);
        boardpage.addAnotherList(secondListName)
                .moveCard();
        Assertions.assertEquals(newCardName, boardpage.getCardNameOnSecondList());
    }


    @Test
    @Order(4)
    public void testSearchBoardByName() {
        CurrentBoardPage boardsPage = mainPage.clickLoginButton()
                .setLoginField().searchBoardByName(newBoardName);

        String actualPageName = boardsPage.getPageName();
        Assertions.assertEquals(newBoardName, actualPageName);
    }
    @Test
    @Order(4)
    public void testSearchCardByName() {

        CurrentBoardPage boardsPage = mainPage.clickLoginButton()
                .setLoginField().searchCardByName(newCardName).closeCardWindow();
        Assertions.assertEquals(newCardName, boardsPage.getCardNameOnSecondList());
    }

    @Test
    @Order(5)
    public void testDeleteBoard() {
        String actualDeletePage = "name12 is closed.";
        CurrentBoardPage boardpage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);

        DeleteBoardPage deletePage= boardpage.deleteBoard();
        deletePage.deleteBoard();
        Assertions.assertEquals(actualDeletePage, deletePage.checkDeleteBoard());

    }

}
