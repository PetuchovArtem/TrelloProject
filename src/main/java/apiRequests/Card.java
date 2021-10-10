package apiRequests;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Card {

    private final String KEY = "0a9e486762e8fec2cd7d6327d23869e1";
    private final String TOKEN = "e19d1c3441e45d4a21d4e1f72a7144e4c51dfd433cbb0171454314ec08ca81e1";

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
//        System.out.println(responseJson);
        JSONArray body = new JSONArray(responseJson);

        JSONObject album = body.getJSONObject(0);

        String NewCardId = album.getString("id");
//        System.out.println(NewCardId);
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
//        System.out.println(response.getStatusLine());
           JSONArray body = new JSONArray(responseJson);

           JSONObject album = body.getJSONObject(0);

           String NewListId = album.getString("idList");
//           System.out.println(NewListId);
           return NewListId;
    }

    public String SearchCardByNameApi(String cardName) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getCard = new HttpGet("https://trello.com/1/search");
        URI uri = new URIBuilder(getCard.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("query", cardName)
                .build();
        ((HttpRequestBase) getCard).setURI(uri);
        HttpResponse response = httpClient.execute(getCard);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);

        JSONObject obj = new JSONObject(content);
        System.out.println(obj.toString());
        JSONArray arr = obj.getJSONArray("cards");
        String card_id = arr.getJSONObject(0).getString("id");

        return card_id;
    }


}
