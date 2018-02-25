package com.example.alex.gitreferencelab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alex on 2/24/18.
 */

public class GitReferenceAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<GitReference> mDataSource;

    public GitReferenceAdapter(Context context, ArrayList<GitReference> gitReferences){
        mContext = context;
        mDataSource = gitReferences;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return mDataSource.size();

    }
    @Override
    public Object getItem(int i){
        return mDataSource.get(i);
    }
    @Override
    public long getItemId(int i){
        return i;
    }

    public View getView(int position, View view, ViewGroup viewGroup){
       View rowView = mInflater.inflate(R.layout.git_reference_layout, viewGroup,false);

        TextView commandView = rowView.findViewById(R.id.command);
        TextView exampleView = rowView.findViewById(R.id.example);
        TextView explanationView = rowView.findViewById(R.id.explanation);
      //  TextView sectionView = rowView.findViewById(android.R.id.text4);

        GitReference gr = (GitReference) getItem(position);
        commandView.setText("Command: " + gr.getCommand());
        exampleView.setText("Example: " + gr.getExample());
        explanationView.setText("Explanation: " + gr.getExplanation());
      //  sectionView.setText(gr.getSection());


        return rowView;
    }
}
