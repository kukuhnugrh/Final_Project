package com.example.final_project;

public class Validation {

    public boolean isValid(String uname, String pword){
        if(uname.equals("imTheKing") && pword.equals("alwaysToxic")){
            return true;
        }
        return false;
    }
}
