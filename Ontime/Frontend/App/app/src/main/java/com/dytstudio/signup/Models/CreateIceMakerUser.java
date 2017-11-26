package com.dytstudio.signup.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mo on 24/11/2017.
 */

public class CreateIceMakerUser {



    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("passwordvalidate")
    @Expose
    private String passwordvalidate;
    @SerializedName("givenName")
    @Expose
    private String givenName;
    @SerializedName("familyName")
    @Expose
    private String familyName;



    public CreateIceMakerUser(String username, String email, String password, String passwordvalidate, String givenName, String familyName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordvalidate = passwordvalidate;
        this.givenName = givenName;
        this.familyName = familyName;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
