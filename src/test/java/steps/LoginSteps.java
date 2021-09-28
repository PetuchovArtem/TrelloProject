package steps;

import io.cucumber.java.en.When;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoginSteps {

    public final String KEY= "0a9e486762e8fec2cd7d6327d23869e1";
    public final String TOKEN= "e19d1c3441e45d4a21d4e1f72a7144e4c51dfd433cbb0171454314ec08ca81e1";


    @When("a user is logged in Trello")
    public void  authorize () throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet tokenRequest = new HttpGet("https://api.trello.com/1/members/me/");
        URI uri = new URIBuilder(tokenRequest.getURI())
                .addParameter("key", KEY)
                .addParameter("token", TOKEN)
                .build();
        ((HttpRequestBase) tokenRequest).setURI(uri);
        HttpResponse response = httpClient.execute(tokenRequest);
//        HttpEntity entity = response.getEntity();
//        String responseString = EntityUtils.toString(entity, "UTF-8");
//        System.out.println(responseString);

    }

}
