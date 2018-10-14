package com.salsal.school.teacher.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnNotifClickListener;
import com.salsal.school.teacher.model.NotificationRes;
import com.salsal.school.teacher.utils.Utils;

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
        holder.txtDate.setText(Utils.convertBirthdayToString(itemList.getCreatedAt()));
        holder.txtDesc.setText(itemList.getMessage());
        holder.txtSender.setText(itemList.getRecipientName() != null ? itemList.getRecipientName() : itemList.getSenderName() + "(" + itemList.getRoleTitle() + ")");
        holder.txtTitle.setText(itemList.getTitle());
        String[] testArray = holder.txtDate.getContext().getResources().getStringArray(R.array.notification_type);

        switch (itemList.getType()) {
            case 1:
                holder.txtType.setText(testArray[0].split("-")[0]);
                break;
            case 2:
                holder.txtType.setText(testArray[1].split("-")[0]);
                break;
        }
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
            if (listNotif.get(getAdapterPosition()).getRecipientName() == null)
                notifClickListener.clicked(listNotif.get(getAdapterPosition()));

        }
    }
}
