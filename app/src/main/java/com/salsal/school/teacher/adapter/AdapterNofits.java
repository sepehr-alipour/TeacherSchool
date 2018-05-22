package com.salsal.school.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnNotifClickListener;
import com.salsal.school.teacher.model.NotificationRes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNofits extends RecyclerView.Adapter<AdapterNofits.ViewHolder> {

    private final List<NotificationRes.DataBean> listNotif;
    private OnNotifClickListener notifClickListener;

    public AdapterNofits(List<NotificationRes.DataBean> notifs, OnNotifClickListener listener) {
        listNotif = notifs;
        notifClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final NotificationRes.DataBean itemList = listNotif.get(position);
        holder.txtDate.setText(itemList.getCreatedAt());
        holder.txtDesc.setText(itemList.getMessage());
        holder.txtSender.setText(itemList.getId() + "");
        holder.txtTitle.setText(itemList.getTitle());
        holder.txtType.setText(itemList.getType() + "");
    }

    @Override
    public int getItemCount() {
        return listNotif.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        @BindView(R.id.txtDate)
        TextView txtDate;

        @BindView(R.id.txtDesc)
        TextView txtDesc;

        @BindView(R.id.txtSender)
        TextView txtSender;

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
            notifClickListener.clicked(listNotif.get(getAdapterPosition()));

        }
    }
}
