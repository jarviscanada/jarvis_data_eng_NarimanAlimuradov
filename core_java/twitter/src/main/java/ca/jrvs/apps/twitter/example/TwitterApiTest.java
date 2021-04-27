package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class TwitterApiTest {

    private static String CONSUMER_KEY = System.getenv("consumerKey");
    private static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private static String ACCESS_TOKEN = System.getenv("accessToken");
    private static String TOKEN_SECRET = System.getenv("tokenSecret");

    public static void main(String[] args) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String status = "1382706223166992388";
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/show.json?id=" + percentEscaper.escape(status));
        consumer.sign(request);

        System.out.println("HTTP HEADERS:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
//apikey apisecret bearertoken accesstoken tokensecret
//BT0XIKu9fzhBtL1Ntwak8UPP3
//faUoCH8kcb0NVNby5uhYdoX7qrz0O5PBatUcr6sR9E4fDKGibe
//AAAAAAAAAAAAAAAAAAAAAOJuOAEAAAAAdSgAwjSgSDiNHjo8fvxAkZD%2B1r8%3D3oh3zU0lsCKravL1iq83sf2kas7bDJhR2kr2GYURgdxTSINBhf
//780963541083889665-J0EgqrCq4ZLO8P5Y9YPdt2ySeJZRV3O
//Q0tigGBr8JCSUdSCBrykpSu4cXjxNKRSKYo43j5oWqtcT