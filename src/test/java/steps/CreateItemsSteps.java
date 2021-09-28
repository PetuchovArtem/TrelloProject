package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.minidev.json.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CreateItemsSteps {

    public final String KEY = "0a9e486762e8fec2cd7d6327d23869e1";
    public final String TOKEN = "e19d1c3441e45d4a21d4e1f72a7144e4c51dfd433cbb0171454314ec08ca81e1";
    public final String DEFAULTLIST = "false";

    public static String boardId = "";
    public static String listid = "";
    public static String newListid = "";
    public static String secondListId = "";
    public static String cadrId = "";
    public static String newBoardName = "NewBoardApi";
    public static String newListName = "newListName1";
    public static String secondListName = "newListName2";
    public static String newCardName = "newCardApi";
    public static int status = 0;

    @And("user is creating a board with name")
    public String createBoarApi() throws IOException, URISyntaxException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.trello.com/1/boards/");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("name", newBoardName)
                .addParameter("token", TOKEN)
                .addParameter("defaultLists", DEFAULTLIST)
                .build();

        ((HttpRequestBase) httpPost).setURI(uri);
        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";

        JSONArray body = new JSONArray(responseJson);

        JSONObject album = body.getJSONObject(0);
        boardId = album.getString("id");
//        System.out.println(boardId);
        return boardId;
    }


    @And("user is creating a new list")
    public String createNewListApi() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.trello.com/1/lists");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("idBoard", boardId)
                .addParameter("name", newListName)
                .build();

        ((HttpRequestBase) httpPost).setURI(uri);
        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";
//        System.out.println(responseJson);
        JSONObject album = new JSONObject(content);
        listid = album.getString("id");
//        System.out.println(NewListId);
        return listid;
    }


    @And("user is creating a second list")
    public String createSecondListApi() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.trello.com/1/lists");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("idBoard", boardId)
                .addParameter("name", secondListName)
                .build();

        ((HttpRequestBase) httpPost).setURI(uri);
        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";
//        System.out.println(responseJson);
        JSONObject album = new JSONObject(content);
        secondListId = album.getString("id");
//        System.out.println(NewListId);
        return secondListId;
    }


    @And("user is creating a new card")
    public String createNewCardApi() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.trello.com/1/cards");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("idList", listid)
                .addParameter("name", newCardName)
                .build();

        ((HttpRequestBase) httpPost).setURI(uri);
        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";
//        System.out.println(responseJson);
        JSONArray body = new JSONArray(responseJson);

        JSONObject album = body.getJSONObject(0);

        cadrId = album.getString("id");
//        System.out.println(NewCardId);
        return cadrId;

    }


    @And("user is moving card in new list")
    public String moveCard() throws URISyntaxException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String urlForCreate= "https://api.trello.com/1/cards/"+cadrId;
        HttpPut httpPut = new HttpPut(urlForCreate);
        httpPut.setHeader("Client_id", "base64Client_id");
        httpPut.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPut.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("idList", secondListId)
                .build();

        ((HttpRequestBase) httpPut).setURI(uri);
        HttpResponse response = httpClient.execute(httpPut);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";
//        System.out.println(response.getStatusLine());
        JSONArray body = new JSONArray(responseJson);

        JSONObject album = body.getJSONObject(0);

        newListid = album.getString("idList");
//           System.out.println(NewListId);
        return newListid;
    }

    @And("user is deleting last board")
    public int DeleteBoardApi() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete delBoard = new HttpDelete("https://api.trello.com/1/boards/" + boardId);
        URI uri = new URIBuilder(delBoard.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .build();
        ((HttpRequestBase) delBoard).setURI(uri);
        HttpResponse response = httpClient.execute(delBoard);

        status = response.getStatusLine().getStatusCode();
        return status;
    }


    @Then("card is moved")
    public void assertCardIsMovedApi (){
        Assertions.assertEquals(secondListId, newListid);
    }

    @Then("board is deleted")
    public void assertDeletBoardApi (){
        Assertions.assertEquals(200, status);
    }

    @Then("board is created")
    public void assertCreateBoarApi (){
        Assertions.assertNotEquals(null, boardId);
    }


    @Then("card is created")
    public void assertCreateCardApi (){
        Assertions.assertNotEquals(null, cadrId);
    }
}
