package apiPages;

import net.minidev.json.parser.ParseException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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


public class Board {


    public final String KEY = "0a9e486762e8fec2cd7d6327d23869e1";
    public final String TOKEN = "e19d1c3441e45d4a21d4e1f72a7144e4c51dfd433cbb0171454314ec08ca81e1";
    public final String DEFAULTLIST = "false";


    public String createBoarApi(String boardName) throws IOException, URISyntaxException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.trello.com/1/boards/");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", "base64Client_secret");
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("name", boardName)
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
        String boardId = album.getString("id");
//        System.out.println(boardId);

        return boardId;
    }

    //    String searchParam


    public String SearchBoardByNameApi(String boardName) throws URISyntaxException, IOException {
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
        String board_id = arr.getJSONObject(0).getString("id");

        return board_id;
    }


    public int DeleteBoardApi(String boardId) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete delBoard = new HttpDelete("https://api.trello.com/1/boards/" + boardId);
        URI uri = new URIBuilder(delBoard.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .build();
        ((HttpRequestBase) delBoard).setURI(uri);
        HttpResponse response = httpClient.execute(delBoard);

        int status = response.getStatusLine().getStatusCode();
        return status;
    }

}

