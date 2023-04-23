package com.omeryildizce.fitnessapp.recyclerview;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.omeryildizce.fitnessapp.FitnessMove;
import com.omeryildizce.fitnessapp.R;

import java.util.ArrayList;

public class FitnessPictureAdapter extends RecyclerView.Adapter<FitnessPictureViewHolder> implements View.OnClickListener {

    private ArrayList<FitnessMove> fitnessMoves;
    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;
    private MyListener myListener;

    public FitnessPictureAdapter(AppCompatActivity appCompatActivity, MyListener myListener) {
        this.appCompatActivity = appCompatActivity;
        this.myListener = myListener;
        layoutInflater = appCompatActivity.getLayoutInflater();
        fitnessMoves = new ArrayList<>();
    }

    public ArrayList<FitnessMove> getFitnessMoves() {
        return fitnessMoves;
    }

    @NonNull
    @Override
    public FitnessPictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listview = layoutInflater.inflate(R.layout.recyclerview_fitness_pictures_list,parent,false);
        listview.setOnClickListener(this);

        return new FitnessPictureViewHolder(listview);
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessPictureViewHolder holder, int position) {
        holder.getMoves(appCompatActivity,fitnessMoves.get(position));
    }

    @Override
    public int getItemCount() {
        return fitnessMoves.size();
    }

    @Override
    public void onClick(View v) {

        if (v.getTag() instanceof FitnessMove) {
            FitnessMove fitnessMove = (FitnessMove) v.getTag();
            myListener.MyListener(fitnessMove);
        }
    }

    public interface MyListener {
        public void MyListener(FitnessMove fitnessMove);
    }
}
