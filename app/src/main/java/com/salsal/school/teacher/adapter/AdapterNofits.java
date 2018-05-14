package com.salsal.school.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnNotifClickListener;
import com.salsal.school.teacher.model.ClsNotification;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNofits extends RecyclerView.Adapter<AdapterNofits.ViewHolder> {

    private final List<ClsNotification> listNotif;
    private OnNotifClickListener notifClickListener;

    public AdapterNofits(List<ClsNotification> notifs, OnNotifClickListener listener) {
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

        final ClsNotification itemList = listNotif.get(position);
        holder.txtDate.setText(itemList.getDate());
        holder.txtDesc.setText(itemList.getDescription());
        holder.txtSender.setText(itemList.getSender());
        holder.txtTitle.setText(itemList.getTitle());
        holder.txtType.setText("عمومی");
        holder.notifLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifClickListener.clicked(itemList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotif.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder

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

        @BindView(R.id.notifLayout)
        CardView notifLayout;


        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
