package com.id.maris.suittest.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.id.maris.suittest.R;
import com.id.maris.suittest.model.EventModel;

/**
 * Created by almas on 06-Sep-16.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvEventName;
    private final TextView tvEventDate;
    private final ImageView imageViewEvent;

    public EventViewHolder(View itemView){
        super(itemView);

        tvEventName = (TextView) itemView.findViewById(R.id.tvEventName);
        tvEventDate = (TextView) itemView.findViewById(R.id.tvEventDate);
        imageViewEvent = (ImageView) itemView.findViewById(R.id.imgEvent);
    }

    public void bind(EventModel model) {
        tvEventName.setText(model.getName());
        tvEventDate.setText(model.getDate());
        model.getImage(imageViewEvent);

    }
}
