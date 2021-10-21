package com.example.currencies;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class JSONParser {
    static InputStream sInputStream = null;
    static JSONObject sReturnJSONObject = null;
    static String sRawJsonString ="";
    public JSONParser(){}

    public JSONObject getJSONFromUrl(String url) {
        try {
            //url="https://openexchangerates.org/api/currencies.json?app_id=abc6652028f3483e9d5cc118279eb49b";
            URL myURL = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) myURL.openConnection();
            urlConnection.connect();
            sInputStream = urlConnection.getInputStream();

            BufferedReader reader;

            reader = new BufferedReader(new InputStreamReader(sInputStream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }
            sRawJsonString=buffer.toString();
            //sReturnJSONObject = new JSONObject(sRawJsonString);
        } catch (IOException e) {
            e.printStackTrace();


        } catch (Exception e) {
            Log.e("Error reading from Buffer: " + e.toString(), this.getClass().getSimpleName());        }

        try {
            sReturnJSONObject = new JSONObject(sRawJsonString);
        } catch (JSONException e) {
            Log.e("Parser", "Error when parsing data " + e.toString());
        }

            return sReturnJSONObject;
    }
}
