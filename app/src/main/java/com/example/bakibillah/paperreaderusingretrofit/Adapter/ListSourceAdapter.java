package com.example.bakibillah.paperreaderusingretrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakibillah.paperreaderusingretrofit.Common.Common;
import com.example.bakibillah.paperreaderusingretrofit.Interface.IconBetterIdeaService;
import com.example.bakibillah.paperreaderusingretrofit.Interface.ItemClickListener;
import com.example.bakibillah.paperreaderusingretrofit.Model.IconBetterIdea;
import com.example.bakibillah.paperreaderusingretrofit.Model.Website;
import com.example.bakibillah.paperreaderusingretrofit.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BakiBillah on 10/6/2017.
 */

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder> {

    private Context context;
    private Website website;
    private IconBetterIdeaService mIconService;

    public ListSourceAdapter(Context context, Website website) {
        this.context = context;
        this.website = website;

        mIconService = Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_layout,parent,false);
        return new ListSourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {
        StringBuilder iconBetterAPI = new StringBuilder("https://icons.better-idea.org/allicons.json?url=");
        iconBetterAPI.append(website.getSources().get(position).getUrl());

        mIconService.getIconUrl(iconBetterAPI.toString())
                .enqueue(new Callback<IconBetterIdea>() {
                    @Override
                    public void onResponse(Call<IconBetterIdea> call, Response<IconBetterIdea> response) {
                        if (response.body().getIcons().size()>0){
                            Picasso.with(context)
                                    .load(response.body().getIcons().get(0).getUrl())
                                    .into(holder.source_image);
                        }
                    }

                    @Override
                    public void onFailure(Call<IconBetterIdea> call, Throwable t) {

                    }
                });

        holder.source_title.setText(website.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position, boolean isLongClick) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return website.getSources().size();
    }
}
