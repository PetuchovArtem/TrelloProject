package tests.api;

import net.minidev.json.parser.ParseException;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import apiPages.Board;
import apiPages.Card;
import apiPages.Lists;
import tests.ui.TrelloTest;

import java.io.IOException;
import java.net.URISyntaxException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloApiTests {


    public static Logger logger = Logger.getLogger(TrelloApiTests.class);


    Board boardApi=new Board();
    Card cardApi = new Card();
    Lists listApi = new Lists();
    private static String boardId = "";
    private static String listid = "";
    private static String secondListId = "";
    private static String cadrId = "";
    private static String newBoardName = "NewBoardApi";
    private final String newListName = "newListName1";
    private static String secondListName = "newListName2";
    private final String newCardName = "newCardApi";

    @Test
    @Tags(value =
            {       @Tag("API"),
                    @Tag("APIandUI"),
                    @Tag("ALLAPI")
            })
    @Order(1)
    public void testCreateBoardApi() throws ParseException, IOException, URISyntaxException {
        logger.info("testCreateBoardApi is started");
        boardId = boardApi.createBoarApi(newBoardName);
        logger.debug("createBoarApi request is sended");
        Assertions.assertNotEquals(null, boardId);
        logger.debug("board request is created. Id = "+ boardId);
        logger.info("testCreateBoardApi is finished");
    }

    @Test
    @Tags(value =
            {       @Tag("API"),
                    @Tag("APIandUI"),
                    @Tag("ALLAPI")
            })
    @Order(2)
    public void testCreateNewListWithCardApi() throws IOException, URISyntaxException{
        logger.info("testCreateNewListWithCardApi is started");
        listid = listApi.createNewListApi(boardId, newListName);
        logger.debug(newListName + " list is created");
        logger.debug( "list id = "+ listid);
        cadrId = cardApi.createNewCardApi(listid, newCardName);
        logger.debug(newCardName + " card is created");
        logger.debug( "cadr id = "+ cadrId);
        Assertions.assertNotEquals(null, listid);
        Assertions.assertNotEquals(null, cadrId);
        logger.info("testCreateNewListWithCardApi is finished");
    }

    @Test
    @Tags(value =
            {       @Tag("API"),
                    @Tag("APIandUI"),
                    @Tag("ALLAPI")
            })
    @Order(3)
    public void testMoveCardApi() throws IOException, URISyntaxException{
        logger.info("testMoveCardApi is started");
        secondListId = listApi.createNewListApi(boardId, secondListName);
        logger.debug(secondListName + "list is created");
        logger.debug( "second list id = "+ secondListId);
        String movecard = cardApi.moveCard(secondListId, cadrId);
        logger.debug(cadrId + " card is moved");
        Assertions.assertEquals(secondListId, movecard);
        logger.info("testMoveCardApi is finished");
    }

    @Test
    @Tags(value =
            {       @Tag("API"),
                    @Tag("APIandUI"),
                    @Tag("ALLAPI")
            })
    @Order(4)
    public void testSearchBoardByNameApi() throws IOException, URISyntaxException{
        logger.info("testSearchBoardByNameApi is started");
        String searchBoardId = boardApi.SearchBoardByNameApi(newBoardName);
        logger.debug(newBoardName + " board is founded");
        Assertions.assertEquals(boardId, searchBoardId);
        logger.info("testSearchBoardByNameApi is finished");
    }

    @Test
    @Tags(value =
            {       @Tag("ALLAPI"),
                    @Tag("APIandUI")
            })
    @Order(4)
    public void testSearchCardByNameApi() throws IOException, URISyntaxException{
        logger.info("testSearchCardByNameApi is started");
        String searchCardId = cardApi.SearchCardByNameApi(newCardName);
        logger.debug(newCardName + " card is founded");
        Assertions.assertEquals(cadrId, searchCardId);
        logger.info("testSearchCardByNameApi is finished");
    }

    @Test
    @Tags(value =
            {       @Tag("API"),
                    @Tag("APIandUI"),
                    @Tag("ALLAPI")
            })
    @Order(5)
    public void testDeleteBoardApi() throws IOException, URISyntaxException{
        logger.info("testDeleteBoardApi is started");
        int delBoardStatus = boardApi.DeleteBoardApi(boardId);
        logger.debug("Board with id = "+boardId + " is deleted");
        Assertions.assertEquals(200, delBoardStatus);
        logger.info("testDeleteBoardApi is finished");
    }

}
