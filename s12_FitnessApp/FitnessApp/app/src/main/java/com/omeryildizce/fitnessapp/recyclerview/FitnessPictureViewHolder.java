package com.omeryildizce.fitnessapp.recyclerview;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.omeryildizce.fitnessapp.FitnessMove;
import com.omeryildizce.fitnessapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FitnessPictureViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    ProgressBar progressBar;

    public FitnessPictureViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.recyclerview_fitness_pictures_list_imageview);
        progressBar = itemView.findViewById(R.id.recyclerview_fitness_pictures_list_progressbar);
    }

    public void getMoves(Context context, FitnessMove fitnessMove) {
        itemView.setTag(fitnessMove);
        Picasso.get().load(fitnessMove.getFitnessPicture()).fit().centerCrop().into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }



}
