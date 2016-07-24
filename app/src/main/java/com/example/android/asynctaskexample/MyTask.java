package com.example.android.asynctaskexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Daniel on 7/20/2016.
 */

//Params, Progress, Result

public class MyTask extends AsyncTask<Void, Integer, String> {
    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;
    String jsonStr;
    int numItems = 0;
    ArrayList<User> userList = new ArrayList<>();

    MyTask(Context context, TextView textView, Button button){
        this.context = context;
        this.textView = textView;
        this.button = button;
    }

    @Override
    protected String doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String booksJsonStr = null;

        //Mimic download process
        int i = 0;

            while(i<100) {
                i += 10;
                publishProgress(i);

            }

        URL url = null;
        try {
            url = new URL("http://174.44.65.70/misc/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        try {
            Log.v("MyTask", "Attempting to Connect");
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.v("MyTask", urlConnection.getURL().toString());


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

            Log.v("MyTask", booksJsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
                Log.v("MyTask", "Connection has been Disconnected");
            }
        }


        return "Download complete ...";



    }

    @Override
    protected void onPreExecute() {
        //First method invokved when we start an async task
        //Initial preperation for asynctask

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in Progress");
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //progressDialog.show();


    }

    @Override
    protected void onPostExecute(String result) {
        //textView.setText(result);
        //textView.setText(jsonStr);
        try {
            JSONObject userJson = new JSONObject(jsonStr);

            numItems = userJson.optInt("totalItems");

            JSONArray userArray = userJson.optJSONArray("user");

            //ArrayList<User> userList = new ArrayList<>();

            for(int i=0; i < numItems; i++){

                JSONObject userData = userArray.getJSONObject(i);

                int id = userData.optInt("id");
                String name = userData.optString("name");
                String email = userData.optString("email");

                userList.add(new User(id, name, email));

                Log.v("JSON", "Original Iteration " + id);

            }

            String test = userList.get(0).toString();
            textView.setText(test);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        button.setEnabled(true);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //Invoked by publish progress
        int progress = values[0];
        //Log.v("MyTask", ""+ values[0]);
        progressDialog.setProgress(progress);
        textView.setText("Download in Progress...");
    }

    /*private ArrayList<User> parseJSON (JSONArray tempArray) throws JSONException {

        ArrayList<User> userList = new ArrayList<>();

        for (int i = 0; i < numItems; i++) {

            JSONObject userData = tempArray.getJSONObject(i);


            //int id = tempArray.optInt(Integer.parseInt("id"));

            int id = tempArray.

            String name = tempArray.optString(Integer.parseInt("name"));

            String email = tempArray.optString(Integer.parseInt("email"));

            userList.add(new User(id, name, email));

            Log.v("JSON", "Iteration " + id);
        }
        return userList;
    }*/
}
