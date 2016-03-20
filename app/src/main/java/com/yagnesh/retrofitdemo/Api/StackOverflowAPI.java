package com.yagnesh.retrofitdemo.Api;

import com.yagnesh.retrofitdemo.model.StackOverflowQuestions;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Yagnesh on 3/19/2016.
 */
public interface StackOverflowAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);

    //post request
    @POST("users/new")
    Call<Response> createUser(@Body RequestBody user);

    //Multipart Request
    @Multipart
    @POST("some/endpoint")
    Call<Response> uploadImage(@Part("description") String description, @Part("image") RequestBody image);


}
