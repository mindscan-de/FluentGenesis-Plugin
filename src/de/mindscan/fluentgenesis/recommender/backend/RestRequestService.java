/**
 * 
 * MIT License
 *
 * Copyright (c) 2020 Maxim Gansert, Mindscan
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package de.mindscan.fluentgenesis.recommender.backend;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 */
public class RestRequestService {

    public final static String SERVER = "http://localhost:8000/";
    public final static String PREDICT_NAMES_GET_PATH = "predictMethodNames/";
    public final static String PREDICT_NAMES_POST_PATH = "predictMethodNamesP/";

    public final static Type listType = new TypeToken<ArrayList<String>>() {
    }.getType();

    /**
     * 
     */
    public RestRequestService() {
        // intentionally left blank
    }

    public List<String> requestMethodNamePredictionsGET() {
        try {
            // TODO: LRU-Cache 64 items / if in cache return the decoded json dataset instead

            URL url = new URL( SERVER + PREDICT_NAMES_GET_PATH + "5" );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod( "GET" );
            con.setRequestProperty( "Content-Type", "application/json" );

            StringBuffer reqestContent = retrieveHTTPResponse( con );

            // TODO: use the LRU-Chache / the caching should be an adapter instead
            return decodeJsonDataToList( reqestContent.toString() );
        }
        catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private StringBuffer retrieveHTTPResponse( HttpURLConnection con ) throws IOException {
        int status = con.getResponseCode();
        System.out.println( "Status is: " + status );

        StringBuffer reqestContent = new StringBuffer();
        try (BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                reqestContent.append( inputLine );
            }
        }
        return reqestContent;
    }

    private List<String> decodeJsonDataToList( String jsonData ) {
        // decode the method names, which are in a simple list right now
        Gson gson = new Gson();
        List<String> predictedMethodNames = gson.fromJson( jsonData, listType );

        System.out.println( predictedMethodNames.toString() );
        return predictedMethodNames;
    }

    public List<String> requestMethodNamePredictionsPOST( String methodBody, int maxPredictions ) {
        try {
            Map<String, String> parameters = new LinkedHashMap<>();

            // TODO: LRU-Cache 64 items / if in cache return the decoded json dataset instead

            // SEND THE MTEHOD BODY VIA POST body
            parameters.put( "body", methodBody );

            URL url = new URL( SERVER + PREDICT_NAMES_POST_PATH + Integer.toString( maxPredictions ) );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod( "POST" );

            sendPOSTRequestData( con, parameters );

            // retrieve the response
            StringBuffer reqestContent = retrieveHTTPResponse( con );

            // TODO: use the LRU-Chache / the caching should be an adapter instead 
            return decodeJsonDataToList( reqestContent.toString() );
        }
        catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private void sendPOSTRequestData( HttpURLConnection con, Map<String, String> parameters ) throws IOException {
        // what we accept: accept: application/json
        con.setRequestProperty( "accept", "application/json" );
        // how we deliver: Content-type: x-www-form-urlencoded
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );

        // send the encoded data 
        con.setDoOutput( true );
        DataOutputStream out = new DataOutputStream( con.getOutputStream() );
        out.writeBytes( buildUrlEncodedParameters( parameters ) );
        out.flush();
        out.close();
    }

    private String buildUrlEncodedParameters( Map<String, String> parameters ) {
        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> element : parameters.entrySet()) {
            builder.append( URLEncoder.encode( element.getKey(), StandardCharsets.UTF_8 ) ).append( "=" )
                            .append( URLEncoder.encode( element.getValue(), StandardCharsets.UTF_8 ) ).append( "&" );
        }

        if (builder.length() > 0) {
            // we remove the tailing ampersand.
            builder.deleteCharAt( builder.length() - 1 );
        }

        return builder.toString();

    }
}
