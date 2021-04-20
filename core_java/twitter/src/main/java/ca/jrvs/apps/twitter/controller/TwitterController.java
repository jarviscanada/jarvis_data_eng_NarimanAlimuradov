package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";
    private Service service;

    @Autowired
    public TwitterController(Service service){
        this.service = service;
    }

    @Override
    public Tweet postTweet(String[] args) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        if (args.length < 1){
            throw new IllegalArgumentException("Please provide text for the Tweet");
        }
        String text = args[0];
        Tweet tweet = new Tweet();
        tweet.setText(text);
        if (args.length > 1) {
            String coords = args[1];
            String[] coordsArr = coords.split(COORD_SEP);
            Coordinates finalCoords = new Coordinates();
            List<Double> coordsList = new ArrayList<>();
            for (String coord : coordsArr) {
                coordsList.add(Double.parseDouble(coord));
            }
            finalCoords.setCoordinates(coordsList);
            tweet.setCoordinates(finalCoords);
        }
        return service.postTweet(tweet);
    }

    @Override
    public Tweet showTweet(String[] args) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        if (args.length < 1){
            throw new IllegalArgumentException("Please provide a Tweet ID");
        }
        String id = args[0];

        if (args.length > 1){
            String[] fields = args[1].split(COMMA);
            return service.showTweet(id, fields);
        }

        return service.showTweet(id, null);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        if (args.length < 1){
            throw new IllegalArgumentException("Please provide a Tweet ID");
        }
        String[] tweetIDs = args[0].split(COMMA);
        return service.deleteTweets(tweetIDs);
    }
}
