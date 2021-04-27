package ca.jrvs.apps.twitter.dao;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {

    @Test
    public void httpPost() throws IOException, URISyntaxException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");
        TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        HttpResponse res = helper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=API_Post"));
        System.out.println(EntityUtils.toString(res.getEntity()));
    }

    @Test
    public void httpGet() throws IOException, URISyntaxException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");
        TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        HttpResponse res = helper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=1382768599908110344"));
        System.out.println(EntityUtils.toString(res.getEntity()));
    }
}