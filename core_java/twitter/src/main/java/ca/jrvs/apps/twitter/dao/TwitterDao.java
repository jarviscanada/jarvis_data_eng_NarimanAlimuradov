package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterDao implements CrdDao<Tweet, String> {
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    /**
     * Create a tweet request using the Tweet object provided. Will require the tweet text but coordinates are optional.
     * @param entity Tweet object
     * @return Tweet object
     */
    @Override
    public Tweet create(Tweet entity) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        Coordinates coords = entity.getCoordinates();
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        if (coords != null){
            double longitude = coords.getCoordinates().get(0);
            double latitude = coords.getCoordinates().get(1);
            URI uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(entity.getText()) +
                    AMPERSAND + "long" + EQUAL + longitude + AMPERSAND + "lat" + EQUAL + latitude);
            HttpResponse response = httpHelper.httpPost(uri);
            return parseResponse(response);
        }

        // If no coords provided, simply create tweet without them
        URI uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(entity.getText()));
        HttpResponse response = httpHelper.httpPost(uri);
        return parseResponse(response);
    }

    /**
     * Find a tweet by ID. Will create a GET request.
     * @param s ID of the tweet
     * @return Tweet object
     */
    @Override
    public Tweet findById(String s) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        URI uri = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s);
        HttpResponse response = httpHelper.httpGet(uri);
        return parseResponse(response);
    }

    /**
     * Delete a tweet by ID. Will create a POST request.
     * @param s ID of the tweet
     * @return Tweet object
     */
    @Override
    public Tweet deleteById(String s) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        URI uri = new URI(API_BASE_URI + DELETE_PATH + "/" + s + ".json");
        HttpResponse response = httpHelper.httpPost(uri);
        return parseResponse(response);
    }

    /**
     * Parses JSON response received.
     * @param response HttpResponse object
     * @return Parsed Tweet object
     */
    Tweet parseResponse(HttpResponse response) throws RuntimeException {
        // ensure the response succeeded
        if (response.getStatusLine().getStatusCode() != HTTP_OK){
            throw new RuntimeException("Unsuccessful API call - status code: " + response.getStatusLine().getStatusCode());
        }

        if (response.getEntity() == null){
            throw new RuntimeException("No entities in tweet.");
        }

        try {
            Tweet tweet = JsonParser.toObjectFromJson(EntityUtils.toString(response.getEntity()), Tweet.class);
            return tweet;
        } catch (Exception e){
            throw new RuntimeException("Unable to parse JSON: ", e);
        }
    }

}
