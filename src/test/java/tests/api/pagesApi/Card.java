package tests.api.pagesApi;

import net.minidev.json.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Card {

    public final String KEY = "0a9e486762e8fec2cd7d6327d23869e1";
    public final String TOKEN = "e19d1c3441e45d4a21d4e1f72a7144e4c51dfd433cbb0171454314ec08ca81e1";
    public final String DEFAULTLIST = "false";

    public String createNewCardApi(String idList, String cardName) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.trello.com/1/cards");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("idList", idList)
                .addParameter("name", cardName)
                .build();

        ((HttpRequestBase) httpPost).setURI(uri);
        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";
        System.out.println(responseJson);
        JSONArray body = new JSONArray(responseJson);

        JSONObject album = body.getJSONObject(0);

        String NewCardId = album.getString("id");
        System.out.println(NewCardId);
        return NewCardId;

    }


       public String moveCard(String listId, String cardId) throws URISyntaxException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String urlForCreate= "https://api.trello.com/1/cards/"+cardId;
        HttpPut httpPut = new HttpPut(urlForCreate);
        httpPut.setHeader("Client_id", "base64Client_id");
        httpPut.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPut.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("idList", listId)
                .build();

        ((HttpRequestBase) httpPut).setURI(uri);
        HttpResponse response = httpClient.execute(httpPut);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";
        System.out.println(response.getStatusLine());
           JSONArray body = new JSONArray(responseJson);

           JSONObject album = body.getJSONObject(0);

           String NewListId = album.getString("idList");
           System.out.println(NewListId);
           return NewListId;
    }
}
