package com.example.wetoken_vf;

import com.google.gson.annotations.SerializedName;

public class Reponse {

    @SerializedName("token")
    private String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
