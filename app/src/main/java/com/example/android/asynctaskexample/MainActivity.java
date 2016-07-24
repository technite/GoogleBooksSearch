package com.example.android.asynctaskexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    Button button;
    ListView listview;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        listview = (ListView) findViewById(R.id.bookList);
        editText = (EditText) findViewById(R.id.search_term);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MyTask myTask = new MyTask(MainActivity.this, textview, button);
                String searchTerm = editText.getText().toString().trim().replaceAll(" ","%20");
                ParseJSON myTask = new ParseJSON(MainActivity.this, textview, button, listview, searchTerm);
                myTask.execute();
                button.setEnabled(false);
            }
        });
    }

}
