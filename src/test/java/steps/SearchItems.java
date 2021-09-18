package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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

public class SearchItems {

    public final String KEY = "0a9e486762e8fec2cd7d6327d23869e1";
    public final String TOKEN = "e19d1c3441e45d4a21d4e1f72a7144e4c51dfd433cbb0171454314ec08ca81e1";
    public static String boardName = "NewBoardApi";
    public final String newCardName = "newCardApi";
    public static String foundedBoardId = "";
    public static String foundedCardId = "";


    @And("user is searching a board by name")
    public String SearchBoardByNameApi() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getBoard = new HttpGet("https://trello.com/1/search");
        URI uri = new URIBuilder(getBoard.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("query", boardName)
                .addParameter("board_fields", "name%2Curl%2Cprefs")
//                .addParameter("dsc", "ed85951402843d1b78ca87ed1a5811e8d85b637ca93a9bbba8f0bf0b077a1676")
                .build();
        ((HttpRequestBase) getBoard).setURI(uri);
        HttpResponse response = httpClient.execute(getBoard);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);

        JSONObject obj = new JSONObject(content);
        JSONArray arr = obj.getJSONArray("boards");
        foundedBoardId = arr.getJSONObject(0).getString("id");
        return foundedBoardId;
    }

    @Then("board is founded")
    public void assertFindBoardApi (){
        Assertions.assertNotEquals(null, foundedBoardId);
    }


    @And("user is searching a card by name")
    public String SearchCardByNameApi() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getCard = new HttpGet("https://trello.com/1/search");
        URI uri = new URIBuilder(getCard.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("query", newCardName)
                .build();
        ((HttpRequestBase) getCard).setURI(uri);
        HttpResponse response = httpClient.execute(getCard);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);

        JSONObject obj = new JSONObject(content);
        System.out.println(obj.toString());
        JSONArray arr = obj.getJSONArray("cards");
        foundedCardId= arr.getJSONObject(0).getString("id");

        return foundedCardId;
    }

    @Then("card is founded")
    public void assertFindCardApi (){
        Assertions.assertNotEquals(null, foundedCardId);
    }
}
