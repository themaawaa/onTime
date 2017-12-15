package com.dytstudio.signup.Util;


import com.dytstudio.signup.Issues.Issue;
import com.dytstudio.signup.Models.AccessToken;
import com.dytstudio.signup.Models.Employer;
import com.dytstudio.signup.Models.PostEmployer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mo on 24/11/2017.
 */

public interface APIInterface {


        @POST("/api/Identity/Create")
        Call<PostEmployer> POST_EMPLOYER(@Body PostEmployer postEmployer);

        @FormUrlEncoded
        @POST("/connect/token")
        Call<AccessToken> POST_TOKEN_CALL (@Field("username") String username, @Field("password") String password, @Field("client_id") String client_id, @Field("grant_type") String grant_type, @Field("scope") String scope);


        //EMPLOYERS

        @GET("/api/Employers")
        Call<List<Employer>> GET_EMPLOYERS(@Header("Authorization") String token);

        @POST("/api/Employers/employeetoemployer")
        Call<Void> POST_EMPLOYEE_TO_EMPLOYER (@Header("Authorization") String token, @Query("id") String id);

        @DELETE("/api/Employees")
        Call<Void> DELETE_EMPLOYER(@Header("Authorization") String token);



        //ISSUES

        @POST("/api/Issues")
        Call<Issue> POST_ISSUE(@Header("Authorization") String token , @Body Issue issue);

        @GET("/api/Issues/issuesfromuser")
        Call<List<Issue>> GET_ISSUES(@Header("Authorization") String token);

        @POST("/api/Issues/changestatus")
        Call<Void> CHANGE_ISSUESTATUS(@Header("Authorization") String token, @Query("id") int id);


}