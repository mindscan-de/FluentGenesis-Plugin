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
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 */
public class RestRequestService {

    public final static String SERVER = "http://localhost:8000/";
    public final static String PREDICT_NAMES_PATH = "predictMethodNames/5";

    public final static Type listType = new TypeToken<ArrayList<String>>() {
    }.getType();

    /**
     * 
     */
    public RestRequestService() {
        // TODO Auto-generated constructor stub
    }

    public List<String> requestMethodNamePredictions() {
        try {
            URL url = new URL( SERVER + PREDICT_NAMES_PATH );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod( "GET" );
            con.setRequestProperty( "Content-Type", "application/json" );

            int status = con.getResponseCode();
            System.out.println( "Status is: " + status );

            StringBuffer reqestContent = new StringBuffer();
            try (BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    reqestContent.append( inputLine );
                }
            }

            Gson gson = new Gson();
            List<String> predictedMethodNames = gson.fromJson( reqestContent.toString(), listType );

            System.out.println( predictedMethodNames.toString() );

            return predictedMethodNames;
        }
        catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
