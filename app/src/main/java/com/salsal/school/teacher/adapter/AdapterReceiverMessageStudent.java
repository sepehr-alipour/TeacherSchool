package com.salsal.school.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnReceiverClickListener;
import com.salsal.school.teacher.model.StudentRes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterReceiverMessageStudent extends RecyclerView.Adapter<AdapterReceiverMessageStudent.ViewHolder> {
    private final OnReceiverClickListener clickListener;
    List<StudentRes> messageItems;
    @BindView(R.id.rootItemw)
    LinearLayout rootItemw;
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    public AdapterReceiverMessageStudent(List<StudentRes> messageItems, OnReceiverClickListener listener) {
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
        final StudentRes messageItem = messageItems.get(position);
        holder.txtTitle.setText(messageItem.getName());
        holder.imgIcon.setImageResource(R.drawable.ic_action_student);
        holder.chkItem.setVisibility(View.VISIBLE);
        holder.bind(position);
       
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

        @BindView(R.id.chkItem)
        AppCompatCheckBox chkItem;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        void bind(int position) {
            // use the sparse boolean array to check
            if (!itemStateArray.get(position, false)) {
                chkItem.setChecked(false);
            } else {
                chkItem.setChecked(true);
            }
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                chkItem.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            } else {
                chkItem.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }
        }

    }
}
