package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
    @Mock
    Service service;

    @InjectMocks
    TwitterController controller;

    @Test
    public void postTweet() throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        when(service.postTweet(any())).thenReturn(new Tweet());
        String[] args = {"December", "20.0:20.1"};
        Tweet tweet = controller.postTweet(args);
        assertNotNull(tweet);
    }

    @Test
    public void showTweet() throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        when(service.showTweet(any(), any())).thenReturn(new Tweet());
        String[] args = {"1382768599908110344", "created_at", "id", "id_str", "text"};
        Tweet tweet = controller.showTweet(args);
        assertNotNull(tweet);
    }

    @Test
    public void deleteTweet() throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        when(service.deleteTweets(any())).thenReturn(new ArrayList<Tweet>());
        String[] args = {"1384199594800672775,1384197832756793351"};
        List<Tweet> tweets = controller.deleteTweet(args);
        assertNotNull(tweets);
    }
}