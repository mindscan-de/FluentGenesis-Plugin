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
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 */
public class RestRequestService {

    public final static String SERVER = "http://localhost:8000/";
    public final static String PREDICT_NAMES_PATH = "predictMethodNames/5";

    /**
     * 
     */
    public RestRequestService() {
        // TODO Auto-generated constructor stub
    }

    public void requestMethodNamePredictions() {
        try {
            URL url = new URL( SERVER + PREDICT_NAMES_PATH );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod( "GET" );
            con.setRequestProperty( "Content-Type", "application/json" );

            int status = con.getResponseCode();
            System.out.println( "Status is: " + status );

            StringBuffer content = new StringBuffer();
            try (BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append( inputLine );
                }
            }

            System.out.println( content );

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}