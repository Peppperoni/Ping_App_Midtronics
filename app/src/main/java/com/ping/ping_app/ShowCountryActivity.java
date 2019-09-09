package com.ping.ping_app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class ShowCountryActivity extends AppCompatActivity {

    TextView mTextView;
    String error = ""; // string field
    String countryURL = "https://restcountries.eu/rest/v1/name/";
    String country = "China";
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_country);
        country = getCountyName();

        mTextView = (TextView)findViewById(R.id.listTextView);
        new HttpGetTask().execute();
    }

    private String getDataFromUrl(String CountryUrl) {

        String result = null;
        int resCode;
        InputStream in;
        try {
            URL url = new URL(CountryUrl);
            URLConnection urlConn = url.openConnection();

            HttpsURLConnection httpsConn = (HttpsURLConnection) urlConn;
            httpsConn.setAllowUserInteraction(false);
            httpsConn.setInstanceFollowRedirects(true);
            httpsConn.setRequestMethod("GET");
            httpsConn.connect();
            resCode = httpsConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpsConn.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        in, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                in.close();
                result = sb.toString();
            } else {
                error += resCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void updateUI(String js){
        try{
            JSONObject country = new JSONObject(js.substring(1, js.length()-1));
            //country, capital, population, area, region, sub-region
            String name = country.getString("name");
            String capital = country.getString("capital");
            String population = country.getString("population");
            String area = country.getString("area");
            String region = country.getString("region");
            String subregion = country.getString("subregion");

            mTextView.setText(
                    "name:                      " + name + "\n"
                    +"capital                     " + capital + "\n"
                    +"population                    " + population + "\n"
                    +"area                     " + area + "\n"
                    +"region                    " + region + "\n"
                    +"subregion                       " + subregion
            );
        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public class HttpGetTask extends AsyncTask<URL, Void, String>{
        @Override
        protected String doInBackground(URL... urls) {
            return getDataFromUrl(countryURL+country);
        }

        @Override
        protected void onPostExecute(String s) {
                if (s != null && !s.equals("")) {
                    /////////Need to pass country name here
                    updateUI(s);
                }
                else{
                    mTextView.setText("Country search error");
                }
        }
    }

    private String getCountyName(){
        mIntent = getIntent();
        String countryName = mIntent.getStringExtra("countryName");
        if(mIntent.hasExtra("countryName"))
            return mIntent.getStringExtra("countryName");
        else
            return "error";
    }
}
