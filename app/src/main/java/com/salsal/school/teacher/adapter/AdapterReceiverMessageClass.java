package com.salsal.school.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnReceiverClickListener;
import com.salsal.school.teacher.model.ResClass;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterReceiverMessageClass extends RecyclerView.Adapter<AdapterReceiverMessageClass.ViewHolder> {
    private final OnReceiverClickListener clickListener;
    List<ResClass> messageItems;
    @BindView(R.id.rootItemw)
    LinearLayout rootItemw;

    public AdapterReceiverMessageClass(List<ResClass> messageItems, OnReceiverClickListener listener) {
        this.messageItems = messageItems;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reveiver_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ResClass messageItem = messageItems.get(position);
        holder.txtTitle.setText(messageItem.getName());
        holder.imgIcon.setImageResource(R.drawable.ic_action_class);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.clicked(messageItem);
            }
        });
    }


    @Override
    public int getItemCount() {
        return messageItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder

    {

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.rootItemw)
        LinearLayout rootView;

        @BindView(R.id.imgIcon)
        ImageView imgIcon;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
