package tests.api;

public class ListApi {

    @Test
    public void createNewListApi() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.trello.com/1/lists");
        httpPost.setHeader("Client_id", "base64Client_id");
        httpPost.setHeader("Client_secret", params.getNewBoardId());
        URI uri = new URIBuilder(httpPost.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .addParameter("idBoard", "61444230d8d1b5122af9de71")
                .addParameter("name", NEWLISTNAME)
                .build();

        ((HttpRequestBase) httpPost).setURI(uri);
        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        String responseJson = "[" + content + "]";

        JSONObject album = new JSONObject(content);
        NewListId = album.getString("id");
        System.out.println(NewListId);
        params.setNewListId(NewListId);
        Assertions.assertNotEquals(null, NewListId);

    }

}
