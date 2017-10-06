package com.example.bakibillah.paperreaderusingretrofit.Interface;

import com.example.bakibillah.paperreaderusingretrofit.Model.IconBetterIdea;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by BakiBillah on 10/6/2017.
 */

public interface IconBetterIdeaService {

    @GET
    Call<IconBetterIdea> getIconUrl(@Url String url);
}
