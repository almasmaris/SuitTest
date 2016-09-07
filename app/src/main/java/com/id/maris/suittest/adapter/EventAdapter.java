package com.id.maris.suittest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.id.maris.suittest.R;
import com.id.maris.suittest.model.EventModel;
import com.id.maris.suittest.viewholder.EventViewHolder;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<EventModel> eventModels;

    public EventAdapter(Context context, List<EventModel> model){
        mLayoutInflater = LayoutInflater.from(context);
        eventModels = new ArrayList<>(model);
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mLayoutInflater.inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        final EventModel model = eventModels.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        eventModels.clear();
        notifyDataSetChanged();
    }

    //Add a list of items
    public void addAll(List<EventModel> list) {
        eventModels.addAll(list);
        notifyDataSetChanged();

    }
}
