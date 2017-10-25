package com.pgmail.martsulg.omertex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lenovo on 24.10.2017.
 */

public class StartAdapter extends RecyclerView.Adapter<StartAdapter.Holder> {

    private ArrayList<String> elementsList;
    private ArrayList<String> urlList;

    public StartAdapter(ArrayList<String> elementsList, ArrayList<String> urlList) {
        this.elementsList = elementsList;
        this.urlList = urlList;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;



    public static class Holder extends  RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemText;

        public Holder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.imageRecycler);
            itemText = (TextView) itemView.findViewById(R.id.textRecycler);
        }
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view,parent,false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        Picasso.with(holder.itemImage.getContext())
                .load(urlList.get(position))
                .into(holder.itemImage, new Callback() {
                    @Override
                    public void onSuccess() {}
                    @Override
                    public void onError() {}
                });

        holder.itemText.setText(elementsList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return elementsList==null ? 0 : elementsList.size();
    }

    interface OnItemClickListener{
        public void onItemClick(int item);
    }
}
