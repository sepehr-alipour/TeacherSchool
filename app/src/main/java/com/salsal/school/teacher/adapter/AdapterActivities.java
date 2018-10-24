package com.salsal.school.teacher.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnDataSelectListener;
import com.salsal.school.teacher.model.ActivityRes;
import com.salsal.school.teacher.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterActivities extends RecyclerView.Adapter<AdapterActivities.ViewHolder> {

    private final List<ActivityRes.DataBean> listActivity;
    private OnDataSelectListener viewClickListener;

    public AdapterActivities(List<ActivityRes.DataBean> activities, OnDataSelectListener listener) {
        listActivity = activities;
        viewClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activities, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ActivityRes.DataBean itemList = listActivity.get(position);
        holder.txtDate.setText(Utils.convertBirthdayToString(itemList.getCreatedAt()));
        holder.txtDesc.setText(itemList.getDesc());
        holder.txtExpireDate.setText(Utils.convertBirthdayToString(itemList.getExpireDate()));
        holder.txtTitle.setText(itemList.getTitle());

        String[] testArray = holder.txtDate.getContext().getResources().getStringArray(R.array.activity_type);

        switch (itemList.getAtypeId()) {
            case 1:
                holder.txtType.setText(testArray[0].split("-")[0]);
                break;
            case 2:
                holder.txtType.setText(testArray[1].split("-")[0]);
                break;
            case 3:
                holder.txtType.setText(testArray[2].split("-")[0]);
                break;
            case 4:
                holder.txtType.setText(testArray[3].split("-")[0]);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listActivity.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        @BindView(R.id.txtDate)
        TextView txtDate;

        @BindView(R.id.txtDesc)
        TextView txtDesc;

        @BindView(R.id.txtExpireDate)
        TextView txtExpireDate;

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtType)
        TextView txtType;


        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            viewClickListener.dataSelected(listActivity.get(getAdapterPosition()));


        }
    }
}
