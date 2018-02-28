package com.example.alex.gitreferencelab;

/**
 * Created by alex on 2/22/18.
 */

public class GitReference {
    private String command;
    private String example;
    private String explanation;
    private String section;

    public GitReference(){
    }

    public String getCommand(){
        return command;
    }
    public String getExample(){
        return example;
    }
    public String getExplanation(){
        return explanation;
    }
    public String getSection(){
        return section;
    }

    public String toString(){
        return section;
    }

    public void setCommand(String s){
        command = s;
    }
    public void setExample(String s){
        example = s;
    }
    public void setExplanation(String s){
        explanation = s;
    }
    public void setSection(String s){
        section = s;
    }
}
