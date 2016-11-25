package com.example.dindin.com.example;

/**
 * Created by Kevin on 11/22/2016.
 */

import java.util.ArrayList;

import com.example.dindin.User;
import com.google.gson.annotations.SerializedName;

public class UserMatchData {

    @SerializedName("matches")
    private ArrayList<User> matches;

    public ArrayList<User> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<User> matches) {
        this.matches = matches;
    }

    @SerializedName("errMsg")
    private String erorrMassage;

    @SerializedName("errNum")
    private int errNum;

    @SerializedName("errFlag")
    private int errFlag;

    public String getErorrMassage() {
        return erorrMassage;
    }

    public void setErorrMassage(String erorrMassage) {
        this.erorrMassage = erorrMassage;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public int getErrFlag() {
        return errFlag;
    }

    public void setErrFlag(int errFlag) {
        this.errFlag = errFlag;
    }

}