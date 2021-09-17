package tests.api;

import net.minidev.json.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
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

import tests.api.params.Params;

public class Board {

    public final String NEWBOARDNAME = "NewBoarApi12345";
    public final String NEWLISTNAME = "newListName1";
    public final String KEY = "0a9e486762e8fec2cd7d6327d23869e1";
    public final String TOKEN = "e19d1c3441e45d4a21d4e1f72a7144e4c51dfd433cbb0171454314ec08ca81e1";
    public final String DEFAULTLIST = "false";
    public String NewBoardId = "";
    public String NewListId = "";
    Params params = new Params();

    @Test
    public void createBoarApi() throws IOException, URISyntaxException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.trello.com/1/boards/");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("name", NEWBOARDNAME)
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
        NewBoardId = album.getString("id");
        System.out.println(NewBoardId);
        params.setNewBoardId(NewBoardId);
        Assertions.assertNotEquals(null, NewBoardId);
    }




}
