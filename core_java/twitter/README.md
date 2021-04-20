# Twitter API App
(50-100 words)
What does this app do? What technoglies you have used? (e.g. Twitter REST API, HTTP client, mvn, Java libs, docker etc..)
The Twitter API app . A user is able to perform basic functions such as posting tweets, reading tweets, and deleting 
tweets through the comfort of their command line.

This **Java** application uses an **HTTP client** to call the **Twitter REST API**. 
Structured using **Maven** and **Springboot** and implements a **Data Access Object** (DAO) design. 
Deployed using on **Docker** and available on **DockerHub**.

## How do I use it?
- how to package your app using mvn?
- how to run your app with docker?

First, pull the docker image from DockerHub:
```
$ docker pull nalimuradov/twitter
```

Next, start a container from that image:
```
$ docker run -e consumerKey=YOUR_CONSUMER_KEY -e consumerSecret=YOUR_CONSUMER_SECRET -e accessToken=YOUR_ACCESS_TOKEN -e tokenSecret=YOUR_TOKEN_SECRET nalimuradov/twitter post|show|delete args
```

The first four arguments are the API keys that need to be set in the environment. These can be obtained from Twitter.

After the environment variables have been set, we can either **post**, **show**, or **delete** a Tweet.

**Posting** a Tweet will require us to pass the Tweet text as an argument. Optionally, we can also pass location 
coordinates, in the form *longitude:latitude*.

```
$ docker run nalimuradov/twitter post "This is my Tweet" 30.1:30.2 
```
> This will post a Tweet that says, "This is my Tweet", from Egypt.


**Showing** a Tweet will require us to pass the Tweet ID as an argument. Optionally, we can pass further **field** arguments,
which will allow us to filter our resulting output to only include the fields we specified.

```
$ docker run nalimuradov/twitter show 1383295895043600384 "created_at,text,retweet_count"
```
> This will show a Tweet made by Elon Musk. The output will only include the date it was created at, 
> the text content, and the retweet count, as per the fields.


**Deleting** a Tweet will require us to pass the Tweet ID as an argument. Multiple Tweets can be deleted at once by passing
a comma separated sequence of Tweet IDs as the argument.

```
$ docker run nalimuradov/twitter delete 1383170143665192961,1382768599908110344
```
> This will delete two Tweets with those Tweet IDs. Only your own Tweets can be deleted.

## Design
### UML diagram
### explain each component(app/main, controller, service, DAO) (30-50 words each)
### Models
Talk about tweet model
### Spring
- How you managed the dependencies using Spring?

## Testing
How did you test you app using Junit and mockito?
All key components were tested using **unit tests** as well as **integration tests**.

## Deployment
How did you dockerize your app.

## Improvements
* **File Path Clarity**
    *  in if input files cannot be found.

* **Nested Directories**
    * Ae folders.

* **File Filtering**
    * ugh.
