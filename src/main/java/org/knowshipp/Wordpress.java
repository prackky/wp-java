package org.knowshipp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowshipp.model.BlogPost;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Wordpress implements RestService {
    private final String userName;
    private final String applicationPassword;
    private final String siteUrl;
    private final ObjectMapper objectMapper;

    public Wordpress(String siteUrl, String userName, String applicationPassword) {
        this.userName = userName;
        this.applicationPassword = applicationPassword;
        this.siteUrl = siteUrl;
        objectMapper = new ObjectMapper();
    }

    public void postBlog(BlogPost blogPost) throws IOException {

        // Set up Basic Authentication
        var encodedAuth = getEncodedAuth(this.userName, this.applicationPassword);
        // Convert PostPayload to JSON
        String jsonPayload = this.objectMapper.writeValueAsString(blogPost);

        var responseCode = post(this.siteUrl, encodedAuth, jsonPayload);

        // Check the response code
        if (responseCode != HttpURLConnection.HTTP_CREATED) {
            throw new IllegalStateException("Unable to post the blogpost, please check again.");
        }
    }


}
