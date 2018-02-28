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
    private ListView gitCommandList;
    private ArrayList<GitReference> gitList;
    private boolean filtered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gitCommandList = findViewById(R.id.git_command_list);

        //initialize jsonString to load data from GitReference.json and load this data into GitReference objects
        processData("GitReference.json");
        gitList = JsonUtils.populateGitReferences(jsonString);

        //Put gitList into an adapter, adapter is set to final so it is referenceable in the onItemLongClick implementation
        final GitReferenceAdapter adapter = new GitReferenceAdapter(this, gitList);
        gitCommandList.setAdapter(adapter);

        //A short click on an Item in the list will bring up a toast message detailing the section field of the item
        gitCommandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Section: " + gitCommandList.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //On a long Click, the listview is filtered so that only commands with the same section as the selected item are shown
        //A subsequent long click will restore the listview to having all GitReferences visible.
        gitCommandList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!filtered){
                    String sectionFilter = gitCommandList.getItemAtPosition(i).toString();
                    gitList = JsonUtils.populateFilteredGitReferences(jsonString, sectionFilter);
                    GitReferenceAdapter filteredAdapter = new GitReferenceAdapter(getApplicationContext(), gitList);
                    gitCommandList.setAdapter(filteredAdapter);
                    Toast.makeText(getApplicationContext(), "Commands Filtered to be from Section: " + sectionFilter, Toast.LENGTH_SHORT).show();
                    filtered = true;
                }else{
                    gitCommandList.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "All Sections Displayed", Toast.LENGTH_SHORT).show();
                    filtered = false;
                }
                return false;
            }
        });





    }

    //Processes the data from the given file using the JsonUtils method parseJsonToString
    //returns the class variable jsonString, which has been set to have data from the given file.
    public String processData(String filename) {
        InputStream is;
        try{
            is = getApplicationContext().getAssets().open(filename);


        jsonString = JsonUtils.parseJsonToString(is);

        Log.i("JSON", jsonString);

    }catch(Exception e){
        Log.i("Error", e.getMessage());
    }

    return jsonString;
    }
}
