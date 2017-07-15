package com.twitter.demo.api;

import com.twitter.demo.models.FollowerListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * this interface contain the service for the network layer
 */
public interface CustomTwitterApiService {

    @GET("/1.1/followers/list.json")
    Call<FollowerListResponse> getUserFollowersList(@Query("user_id") long id,
                                                    @Query("cursor") long cursor);
}
