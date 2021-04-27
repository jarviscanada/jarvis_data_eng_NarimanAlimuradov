package ca.jrvs.apps.twitter.dao;

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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class TwitterHttpHelper implements HttpHelper{

    private OAuthConsumer consumer;
    private HttpClient httpClient;

    /**
     * Class constructor that instantiates the four keys needed to connect to the Twitter API.
     * @param consumerKey API key
     * @param consumerSecret API secret
     * @param accessToken API access token
     * @param tokenSecret API access token secret
     */
    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
        httpClient = HttpClientBuilder.create().build();
    }

    /**
     * Default constructor
     */
    public TwitterHttpHelper(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
        httpClient = HttpClientBuilder.create().build();
    }

    /**
     * Performs and executes a POST request
     * @param uri Identifier used to post a request to the Twitter API.
     * @return HTTP response
     */
    @Override
    public HttpResponse httpPost(URI uri) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {
        HttpPost request = new HttpPost(uri);
        consumer.sign(request);
        HttpResponse response = httpClient.execute(request);
        return response;
    }

    /**
     * Performs and executes a GET request
     * @param uri Identifier used to get a request from the Twitter API
     * @return HTTP response
     */
    @Override
    public HttpResponse httpGet(URI uri) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {
        HttpGet request = new HttpGet(uri);
        consumer.sign(request);
        HttpResponse response = httpClient.execute(request);
        return response;
    }
}
