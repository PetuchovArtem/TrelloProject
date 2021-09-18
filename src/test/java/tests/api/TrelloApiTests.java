package tests.api;

import net.minidev.json.parser.ParseException;

import org.junit.jupiter.api.*;
import apiPages.Board;
import apiPages.Card;
import apiPages.Lists;

import java.io.IOException;
import java.net.URISyntaxException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloApiTests {
    Board boardApi=new Board();
    Card cardApi = new Card();
    Lists listApi = new Lists();
    public static String boardId = "";
    public static String listid = "";
    public static String secondListId = "";
    public static String cadrId = "";
    public static String newBoardName = "NewBoardApi";
    public final String newListName = "newListName1";
    public static String secondListName = "newListName2";
    public final String newCardName = "newCardApi";

    @Test
    @Order(1)
    public void testCreateBoardApi() throws ParseException, IOException, URISyntaxException {
        boardId = boardApi.createBoarApi(newBoardName);
        Assertions.assertNotEquals(null, boardId);
    }

    @Test
    @Order(2)
    public void testCreateNewListWithCardApi() throws IOException, URISyntaxException{
        listid = listApi.createNewListApi(boardId, newListName);
        cadrId = cardApi.createNewCardApi(listid, newCardName);
        Assertions.assertNotEquals(null, listid);
        Assertions.assertNotEquals(null, cadrId);
    }

    @Test
    @Order(3)
    public void testMoveCardApi() throws IOException, URISyntaxException{
        secondListId = listApi.createNewListApi(boardId, secondListName);
        String movecard = cardApi.moveCard(secondListId, cadrId);
        Assertions.assertEquals(secondListId, movecard);
    }

    @Test
    @Order(4)
    public void testSearchBoardByNameApi() throws IOException, URISyntaxException{
        String searchBoardId = boardApi.SearchBoardByNameApi(newBoardName);
        Assertions.assertEquals(boardId, searchBoardId);
    }

    @Test
    @Order(4)
    public void testSearchCardByNameApi() throws IOException, URISyntaxException{
        String searchCardId = cardApi.SearchCardByNameApi(newCardName);
        Assertions.assertEquals(cadrId, searchCardId);
    }

    @Test
    @Order(5)
    public void testDeleteBoardApi() throws IOException, URISyntaxException{
        int delBoardStatus = boardApi.DeleteBoardApi(boardId);
        Assertions.assertEquals(200, delBoardStatus);
    }

}
