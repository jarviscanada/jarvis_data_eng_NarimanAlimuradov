package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.HttpHelper;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URISyntaxException;

public class TwitterCLIBean {
    public static void main(String[] args) throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);
    }

    @Bean
    public TwitterCLIApp twitterCLIApp(Controller controller){
        return new TwitterCLIApp(controller);
    }

    @Bean
    public Controller controller(Service service){
        return new TwitterController(service);
    }

    @Bean
    public Service service(CrdDao dao){
        return new TwitterService(dao);
    }

    @Bean
    public CrdDao crdDao(HttpHelper httpHelper){
        return new TwitterDao(httpHelper);
    }

    HttpHelper helper() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        return new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    }
}
