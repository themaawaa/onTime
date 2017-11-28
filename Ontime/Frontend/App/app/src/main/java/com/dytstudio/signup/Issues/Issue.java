package com.dytstudio.signup.Issues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Mo on 28/11/2017.
 */

public class Issue {
    @SerializedName("issueID")
    @Expose
    public Integer issueID;
    @SerializedName("reason")
    @Expose
    public Reason reason;
    @SerializedName("timestamp")
    @Expose
    public Date date;

    public Issue(Integer issueID, Reason reason, Date date) {
        this.issueID = issueID;
        this.reason = reason;
        this.date = date;
    }
}