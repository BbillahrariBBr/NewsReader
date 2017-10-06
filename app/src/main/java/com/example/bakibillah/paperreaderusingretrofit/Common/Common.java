package com.example.bakibillah.paperreaderusingretrofit.Common;

import com.example.bakibillah.paperreaderusingretrofit.Interface.IconBetterIdeaService;
import com.example.bakibillah.paperreaderusingretrofit.Interface.NewsService;
import com.example.bakibillah.paperreaderusingretrofit.Remote.IconBetterIdeaClient;
import com.example.bakibillah.paperreaderusingretrofit.Remote.RetrofitClient;

/**
 * Created by BakiBillah on 10/6/2017.
 */

public class Common {

    private static final String BASE_URL = "https://newsapi.org/";

    public static  final String API_KEYS = "858bf954238847f89c27f1a1aa39bdc9";

    public static NewsService getNewsService(){
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }
    public static IconBetterIdeaService getIconService(){
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }
}
