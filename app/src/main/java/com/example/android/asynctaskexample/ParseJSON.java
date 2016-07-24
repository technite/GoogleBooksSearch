package com.example.android.asynctaskexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Daniel on 7/22/2016.
 */

public class ParseJSON extends AsyncTask<Void, Integer, String> {
    Context context;
    TextView textView;
    Button button;
    ListView listview;
    ProgressDialog progressDialog;
    String jsonStr;
    int numItems = 0;
    String searchTerm = "";


    ArrayList<Book> books = new ArrayList<>();

    ParseJSON(Context context, TextView textView, Button button){
        this.context = context;
        this.textView = textView;
        this.button = button;
    }

    public ParseJSON(Context context, TextView textView, Button button, ListView listview) {
        this.context = context;
        this.textView = textView;
        this.button = button;
        this.listview = listview;
    }

    public ParseJSON(Context context, TextView textView, Button button, ListView listview, String searchTerm) {
        this.context = context;
        this.textView = textView;
        this.button = button;
        this.listview = listview;
        this.searchTerm = searchTerm;
    }

    @Override
    protected String doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String booksJsonStr = null;

        URL url = null;
        try {
            String linkText = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + searchTerm + "&maxResults=40&key=AIzaSyDIexMpE8g6ewkXNf_OoBcqAfDoZGD1Ee4";


            url = new URL(linkText);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            Log.v("ParseJSON", "Attempting to Connect");
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.v("Connecting to: ", urlConnection.getURL().toString());

        try {
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            booksJsonStr = buffer.toString();
            jsonStr = booksJsonStr;

            books = parserMethod(booksJsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
                Log.v("ParseJSON", "Connection has been Disconnected");
            }
        }

            String test = "";
            for(int i = 0; i < books.size(); i++) {
                test += books.get(i).getmTitle() +"\n";
            }

        //return "Download complete ...";
        return test;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {
        //textView.setText(result);
        button.setEnabled(true);

//
//        LocationAdapter adapter = new LocationAdapter(this, meetings);
//        ListView listview = (ListView) findViewById(R.id.locationList);
//        listview.setAdapter(adapter);

       BookAdapter adapter = new BookAdapter((Activity) context, books);
       listview = (ListView) listview.findViewById(R.id.bookList);
       listview.setAdapter(adapter);

       // ListView listview = (ListView) findViewById(R.id.bookList);


    }

    @Override
    protected void onProgressUpdate(Integer... values) {

    }

    public ArrayList<Book> parserMethod(String jsonString) throws JSONException {
        JSONObject root = new JSONObject(jsonString);
        ArrayList<Book> bookTitles = new ArrayList<Book>();

        int numBooks = root.optInt("totalItems");

        JSONArray itemArray = root.getJSONArray("items");

        for(int i = 0; i < itemArray.length(); i++){

            JSONObject bookJSON = itemArray.getJSONObject(i);
            JSONObject volumeInfo = bookJSON.optJSONObject("volumeInfo");

            String title = volumeInfo.optString("title");

            if(volumeInfo.has("authors")) {
                JSONArray authors = volumeInfo.optJSONArray("authors");
                String author = authors.get(0).toString();

                Book book = new Book(title,author);
                bookTitles.add(book);
            }
        }
        return bookTitles;
    }

}
