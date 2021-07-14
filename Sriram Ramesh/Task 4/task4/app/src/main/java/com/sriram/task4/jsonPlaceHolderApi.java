package com.sriram.task4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface jsonPlaceHolderApi {

    @GET("api/breeds/list/all")
    Call<modelClass> getPosts();

    @GET("api/breed/{breed_name}/images/random")
    Call<Images> getUrl(@Path("breed_name") String breed_name);
}
