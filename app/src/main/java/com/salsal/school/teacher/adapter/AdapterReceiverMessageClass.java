package com.salsal.school.teacher.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnReceiverClickListener;
import com.salsal.school.teacher.model.ClassRes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterReceiverMessageClass extends RecyclerView.Adapter<AdapterReceiverMessageClass.ViewHolder> {
    private final OnReceiverClickListener clickListener;
    List<ClassRes.DataBean> messageItems;
    @BindView(R.id.rootItemw)
    LinearLayout rootItemw;

    public AdapterReceiverMessageClass(List<ClassRes.DataBean> messageItems, OnReceiverClickListener listener) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ClassRes.DataBean messageItem = messageItems.get(position);
        holder.txtTitle.setText(messageItem.getTitle());
        holder.imgIcon.setImageResource(R.drawable.ic_action_class);
    }


    @Override
    public int getItemCount() {
        return messageItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

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
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            clickListener.clicked(messageItems.get(getAdapterPosition()), getAdapterPosition());

        }
    }
}
