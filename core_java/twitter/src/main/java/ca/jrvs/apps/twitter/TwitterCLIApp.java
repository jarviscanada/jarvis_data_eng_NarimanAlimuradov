package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.HttpHelper;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class TwitterCLIApp {

    private final static String CONSUMER_KEY = System.getenv("consumerKey");
    private final static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private final static String ACCESS_TOKEN = System.getenv("accessToken");
    private final static String TOKEN_SECRET = System.getenv("tokenSecret");
    private Controller controller;

    @Autowired
    public TwitterCLIApp(Controller controller){
        this.controller = controller;
    }

    public static void main(String[] args) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        HttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        CrdDao dao = new TwitterDao(helper);
        Service service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp cli = new TwitterCLIApp(controller);
        cli.run(args);
    }

    public void run(String[] args) throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String[] fieldArgs = new String[args.length - 1];
        System.arraycopy(args, 1, fieldArgs, 0, args.length - 1);
        Tweet tweet;
        switch (args[0].toLowerCase()){
            case "post":
                tweet = controller.postTweet(fieldArgs);
                System.out.println(JsonParser.toJson(tweet, true, false));
                break;
            case "show":
                tweet = controller.showTweet(fieldArgs);
                System.out.println(JsonParser.toJson(tweet, true, false));
                break;
            case "delete":
                List<Tweet> tweets = controller.deleteTweet(fieldArgs);
                for (Tweet tw : tweets){
                    System.out.println(JsonParser.toJson(tw, true, false));
                }
                break;
            default:
                throw new IllegalArgumentException("Incorrect argument passed. Valid actions: post | show | delete");
        }
    }
}
