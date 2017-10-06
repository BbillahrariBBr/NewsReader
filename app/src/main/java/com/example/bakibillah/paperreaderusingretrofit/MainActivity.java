package com.example.bakibillah.paperreaderusingretrofit;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bakibillah.paperreaderusingretrofit.Adapter.ListSourceAdapter;
import com.example.bakibillah.paperreaderusingretrofit.Common.Common;
import com.example.bakibillah.paperreaderusingretrofit.Interface.NewsService;
import com.example.bakibillah.paperreaderusingretrofit.Model.Website;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebSite;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    android.app.AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init cache;

        Paper.init(this);

        //init service
        mService = Common.getNewsService();

        // init View

        swipeRefreshLayout =  (SwipeRefreshLayout)findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebSource(true);
            }
        });

        listWebSite = (RecyclerView)findViewById(R.id.list_source);
        listWebSite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listWebSite.setLayoutManager(layoutManager);


        dialog = new SpotsDialog(this);
        loadWebSource(false);

    }

    private void loadWebSource(boolean isRefreshed) {
        if(!isRefreshed){
            String cache = Paper.book().read("cache");
            if (cache!=null && !cache.isEmpty()) //if have cache
            {
                Website website = new Gson().fromJson(cache,Website.class); // conver cache from json to object
                adapter = new ListSourceAdapter(getBaseContext(),website);
                adapter.notifyDataSetChanged();
                listWebSite.setAdapter(adapter);


            }
            else {
                //if not cache
                dialog.show();
                //fetch new data
                mService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {
                        adapter = new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        listWebSite.setAdapter(adapter);

                        //save to cache

                        Paper.book().write("cache",new Gson().toJson(response.body()));
                        dialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                    }
                });
            }
        }
        else // if from swipe to refresh
            {
                dialog.show();
                //fetch new data
                mService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {
                        adapter = new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        listWebSite.setAdapter(adapter);

                        //save to cache

                        Paper.book().write("cache",new Gson().toJson(response.body()));

                        //dismiss refresh progresing
                        swipeRefreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                    }
                });


        }
    }
}
