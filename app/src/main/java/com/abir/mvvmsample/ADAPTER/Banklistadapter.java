package com.abir.mvvmsample.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abir.mvvmsample.MODEL.BANK;
import com.abir.mvvmsample.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Banklistadapter extends RecyclerView.Adapter<Banklistadapter.Todo_View_Holder> {
    Context context;
    List<BANK> bankList;

    public Banklistadapter(List<BANK> to, Context c) {
        bankList = to;
        context = c;
    }

    public class Todo_View_Holder extends RecyclerView.ViewHolder {
        TextView notification_title;
        ImageView notification_image;

        public Todo_View_Holder(View view) {
            super(view);
            notification_title = view.findViewById(R.id.bank_title);
            notification_image = view.findViewById(R.id.bank_details);
        }
    }

    @Override
    public Todo_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_banklist, parent, false);

        return new Todo_View_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final Todo_View_Holder holder, int position) {
        final BANK bodyResponse = bankList.get(position);
        holder.notification_title.setText(bodyResponse.getImageList());
        Glide.with(context).load(bodyResponse.getImageUrl()).apply(RequestOptions.circleCropTransform()).into(holder.notification_image);
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }
}