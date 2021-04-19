package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterService implements Service{

    private CrdDao dao;

    @Autowired
    public TwitterService(CrdDao dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet) throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String text = tweet.getText();
        if (text.length() > 140){
            throw new RuntimeException("ERROR: Text length over 140 character limit.");
        }

        if (tweet.getCoordinates() != null){
            Double longitude = tweet.getCoordinates().getCoordinates().get(0);
            Double latitude = tweet.getCoordinates().getCoordinates().get(1);
            if (longitude < -180 || longitude > 180 || latitude < -90 || latitude > 90){
                throw new RuntimeException("ERROR: Coordinates (longitude/latitude) out of range.");
            }
        }
        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        if (!id.matches("^[0-9]*$")){
            throw new RuntimeException("ERROR: ID must contain numbers only.");
        }

        String[] allFields = {"created_at", "id", "id_str", "text", "entities", "coordinates", "retweet_count", "favorite_count", "favorited", "retweeted"};

        if (fields != null){
            for (String field : fields){
                if (!Arrays.asList(allFields).contains(field)) {
                    throw new RuntimeException("ERROR: Ineligible field specified.");
                }
            }
            Tweet tweet = (Tweet) dao.findById(id);

            return tweet;
        }
        return (Tweet) dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        List<Tweet> deletedTweets = new ArrayList<>();
        for (String id : ids){
            if (!id.matches("^[0-9]*$")){
                throw new RuntimeException("ERROR: ID must contain numbers only.");
            }
            deletedTweets.add((Tweet) dao.deleteById(id));
        }
        return deletedTweets;
    }
}
