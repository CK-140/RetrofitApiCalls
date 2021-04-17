package com.example.retrofitapicalls;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int uId;
    private int id;
    private  String title;
   @SerializedName("Body")
    private  String text;



    public int getuId() {
        return uId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
