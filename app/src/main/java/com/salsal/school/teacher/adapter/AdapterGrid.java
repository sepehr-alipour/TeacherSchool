package com.salsal.school.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnViewClickListener;
import com.salsal.school.teacher.model.GridItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ViewHolder> {

    private final List<GridItem> items;
    private OnViewClickListener clickListener;

    public AdapterGrid(List<GridItem> items, OnViewClickListener listener) {
        this.items = items;
        clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final GridItem itemList = items.get(position);
        holder.txtTitle.setText(itemList.getTitle());
        holder.imgIcon.setImageResource(itemList.getIcon());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {

        @BindView(R.id.txtTitle)
        TextView txtTitle;


        @BindView(R.id.imgIcon)
        ImageView imgIcon;


        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.clicked(items.get(getAdapterPosition()).getType(), v);
        }
    }
}
