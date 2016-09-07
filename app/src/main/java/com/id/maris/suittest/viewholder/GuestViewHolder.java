package com.id.maris.suittest.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.id.maris.suittest.R;
import com.id.maris.suittest.model.GuestModel;

/**
 * Created by almas on 06-Sep-16.
 */

public class GuestViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvGuestName;
    private final TextView tvGuestBirthDate;
    private final ImageView imageViewGuest;

    public GuestViewHolder(View itemView){
        super(itemView);

        tvGuestName = (TextView) itemView.findViewById(R.id.guestname);
        tvGuestBirthDate = (TextView) itemView.findViewById(R.id.birthdate);
        imageViewGuest = (ImageView) itemView.findViewById(R.id.imgGuest);
    }

    public void bind(GuestModel model) {
        tvGuestName.setText(model.getName());
        tvGuestBirthDate.setText(model.getBirthdate());
        model.getImage(imageViewGuest);

    }
}