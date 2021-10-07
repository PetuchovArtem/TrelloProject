package tests.ui;

import com.codeborne.selenide.Configuration;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Flaky;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import pages.CurrentBoardPage;
import pages.DeleteBoardPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloTest {

    public LoginPage mainPage;
    private String newBoardName = "name12";
    private String newListName = "newListName1";
    private String newCardName = "newCardName1";
    private String secondListName = "SecondList";

    public static Logger logger = Logger.getLogger(TrelloTest.class);


//    @BeforeAll
//    public static void setUp() {
//        logger.info("Browser configuration start");
//        Configuration.browser = "chrome";
//        Configuration.startMaximized = true;
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        logger.info("Browser configurated success");
//    }

    @BeforeEach
    public void Autorization() {
        mainPage = open("https://trello.com/", LoginPage.class);
        logger.info("Trello is opened");
    }

    @AfterEach
    public void clouseBrowser(){
        closeWebDriver();
    }

    @Test
    @Tags(value =
            {@Tag("ALLUI"),
                    @Tag("UI"),
                    @Tag("APIandUI")
            })
    @Description("Create new board")
    @Feature(value = "Boards")
    @Order(1)
    public void testCreateNewBoard() {
        logger.info("testCreateNewBoard is started");

        CurrentBoardPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().createNewBoard(newBoardName);

        logger.debug(newBoardName+" board is created");
        String actualPageName = inputLoginPage.getPageName();
        Assertions.assertEquals(newBoardName, actualPageName);
        logger.info("testCreateNewBoard is finished");
    }

    @Test
    @Tags(value =
            {@Tag("ALLUI"),
                    @Tag("UI"),
                    @Tag("APIandUI")
            })
    @Description("Create new list and card")
    @Features(value = {@Feature(value = "Cards"), @Feature(value = "Lists")})
    @Order(2)
    public void testCreateNewListWithCard() {
        logger.info("testCreateNewListWithCard is started");
        CurrentBoardPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);
        inputLoginPage.addAList(newListName);
        logger.debug(newListName+" list is created");
        inputLoginPage.addACard(newCardName);

        logger.debug(newCardName+" card is created");

        String actualListName = inputLoginPage.getListName();
        String actualCardName = inputLoginPage.getCardName();

        Assertions.assertEquals(newListName, actualListName);
        Assertions.assertEquals(newCardName, actualCardName);
        logger.info("testCreateNewListWithCard is finished");
    }

    @Test
    @Tags(value =
            {@Tag("ALLUI"),
                    @Tag("UI"),
                    @Tag("APIandUI")
            })
    @Description("Move card")
    @Feature(value = "Cards")
    @Order(3)
    public void testMoveCard() {
        logger.info("testMoveCard is started");
        CurrentBoardPage boardpage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);
        logger.debug(newBoardName+" board is opened");
        boardpage.addAnotherList(secondListName);
        logger.debug(secondListName+" list is opened");
        boardpage.moveCard();
        logger.debug(newCardName+" card is moved");
        Assertions.assertEquals(newCardName, boardpage.getCardNameOnSecondList());
        logger.info("testMoveCard is finished");
    }


    @Test
    @Tags(value =
            {@Tag("ALLUI"),
                    @Tag("UI"),
                    @Tag("APIandUI")
            })
    @Description("Search board by name")
    @Feature(value = "Search")
    @Order(4)
    public void testSearchBoardByName() {
        logger.info("testSearchBoardByName is started");
        CurrentBoardPage boardsPage = mainPage.clickLoginButton()
                .setLoginField().searchBoardByName(newBoardName);
        logger.debug(newBoardName+" board is founded");
        String actualPageName = boardsPage.getPageName();
        Assertions.assertEquals(newBoardName, actualPageName);
        logger.info("testSearchBoardByName is finished");
    }

    @Test
    @Tags(value =
            {@Tag("ALLUI"),
                   @Tag("APIandUI")
            })
    @Description("Search card by name")
    @Flaky
    @Feature(value = "Search")
    @Order(4)
    public void testSearchCardByName() {
        logger.info("testSearchCardByName is started");
        CurrentBoardPage boardsPage = mainPage.clickLoginButton()
                .setLoginField().searchCardByName(newCardName).closeCardWindow();
        logger.debug(newCardName+" board is founded");
        Assertions.assertEquals(newCardName, boardsPage.getCardNameOnSecondList());
        logger.info("testSearchCardByName is finished");
    }

    @Test
    @Tags(value =
            {@Tag("ALLUI"),
                    @Tag("UI"),
                    @Tag("APIandUI")
            })
    @Description("Delete board")
    @Feature(value = "Boards")
    @Order(5)
    public void testDeleteBoard() {
        logger.info("testDeleteBoard is started");
        String actualDeletePage = "name12 is closed.";
        CurrentBoardPage boardpage = mainPage.clickLoginButton()
                .setLoginField().openCurrentBoard(newBoardName);
        logger.debug(newBoardName+" board is opened");
        DeleteBoardPage deletePage= boardpage.deleteBoard();
        logger.debug("Board is deleted");
        deletePage.deleteBoard();
        Assertions.assertEquals(actualDeletePage, deletePage.checkDeleteBoard());
        logger.info("testDeleteBoard is finished");

    }

}
