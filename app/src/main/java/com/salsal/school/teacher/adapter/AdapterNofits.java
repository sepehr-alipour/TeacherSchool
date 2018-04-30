package com.salsal.school.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.salsal.school.teacher.R;

import java.util.List;

public class AdapterNofits extends RecyclerView.Adapter<AdapterNofits.ViewHolder> {

    private final List<String> listNotif;

    public AdapterNofits(List<String> notifs) {
        listNotif = notifs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.switch_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return listNotif.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder

    {
        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
