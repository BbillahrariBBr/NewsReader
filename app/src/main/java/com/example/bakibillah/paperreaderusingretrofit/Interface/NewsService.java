package com.example.bakibillah.paperreaderusingretrofit.Interface;

import com.example.bakibillah.paperreaderusingretrofit.Model.Website;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BakiBillah on 10/6/2017.
 */

public interface NewsService {
    @GET("v1/sources?language=en")
    Call<Website> getSources();
}
