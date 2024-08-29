package org.knowshipp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public interface RestService {
    default int post(String siteUrl, String encodedAuth, String jsonPayload) {
        int responseCode = 0;
        try {
            var connection = getHttpURLConnection(siteUrl, encodedAuth);
            connection.setRequestMethod("POST");

            // Write the JSON payload
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            responseCode = connection.getResponseCode();
            connection.disconnect();
        } catch (IOException e) {
            responseCode = 500;
        }
        return responseCode;
    }

    default String getEncodedAuth(String userName, String applicationPassword) {
        String auth = userName + ":" + applicationPassword;
        return Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
    }

    private static HttpURLConnection getHttpURLConnection(String siteUrl, String encodedAuth) throws IOException {
        var url = new URL(siteUrl);
        var connection = (HttpURLConnection) url.openConnection();

        // Set up the connection
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

        // Enable input/output streams
        connection.setDoOutput(true);
        return connection;
    }
}
