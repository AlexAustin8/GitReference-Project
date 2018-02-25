package com.example.alex.gitreferencelab;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private String jsonString;
    private TextView text;
    private ListView gitCommandList;
    private ArrayList<GitReference> gitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gitCommandList = findViewById(R.id.git_command_list);


        processData("GitReference.json");
        gitList = JsonUtils.populateGitReferences(jsonString);

        GitReferenceAdapter adapter = new GitReferenceAdapter(this, gitList);
        gitCommandList.setAdapter(adapter);


        gitCommandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Short Click yo", Toast.LENGTH_SHORT).show();
            }
        });

        gitCommandList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "I been told y'all my clicks long", Toast.LENGTH_SHORT).show();
                return false;
            }
        });





    }


    public String processData(String filename) {
        jsonString = "";
        InputStream is;
        try{
       // boolean isFilePresent = JsonUtils.isFilePresent(this, filename)
            is = getApplicationContext().getAssets().open(filename);


        jsonString = JsonUtils.parseJsonToString(is);

        Log.i("JSON", jsonString);

    }catch(Exception e){
        Log.i("Error", e.getMessage());
    }

    return jsonString;
    }
}
