package com.example.bakibillah.paperreaderusingretrofit.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bakibillah.paperreaderusingretrofit.Interface.ItemClickListener;
import com.example.bakibillah.paperreaderusingretrofit.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BakiBillah on 10/6/2017.
 */

public class ListSourceViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

    TextView source_title;
    CircleImageView source_image;

    ItemClickListener itemClickListener;
    public ListSourceViewHolder(View itemView) {
        super(itemView);

        source_image = (CircleImageView)itemView.findViewById(R.id.source_image);
        source_title = (TextView)itemView.findViewById(R.id.source_name);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.OnClick(view,getAdapterPosition(),false);
    }
}
