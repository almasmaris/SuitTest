package com.id.maris.suittest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.id.maris.suittest.R;
import com.id.maris.suittest.model.GuestModel;
import com.id.maris.suittest.viewholder.GuestViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by almas on 06-Sep-16.
 */

public class GuestAdapter extends RecyclerView.Adapter<GuestViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<GuestModel> guestModels;

    public GuestAdapter(Context context, List<GuestModel> model) {
        mLayoutInflater = LayoutInflater.from(context);
        guestModels = new ArrayList<>(model);
    }


    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mLayoutInflater.inflate(R.layout.guest_card, parent, false);
        return new GuestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        final GuestModel model = guestModels.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return guestModels.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        guestModels.clear();
        notifyDataSetChanged();
    }

}
