package com.salsal.school.teacher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnSwitchSelectListener;
import com.salsal.school.teacher.model.StudentRes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class AdapterStudents extends RecyclerView.Adapter<AdapterStudents.ViewHolder> {
    private final List<StudentRes.DataBean> studentList;
    private final OnSwitchSelectListener onSwitchSelectListener;

    @Override
    public AdapterStudents.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student, parent, false);
        return new ViewHolder(view);
    }

    public AdapterStudents(List<StudentRes.DataBean> studentRes, OnSwitchSelectListener listener) {
        this.studentList = studentRes;
        this.onSwitchSelectListener = listener;
    }

    @Override
    public void onBindViewHolder(final AdapterStudents.ViewHolder holder, int position) {
        final StudentRes.DataBean studentItem = studentList.get(position);
        holder.txtTitle.setText(studentItem.getName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_action_profile);
        holder.btnAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnAbsent.setBackgroundResource(R.drawable.shape_blue_fill);
                holder.btnAbsent.setTextColor(holder.btnAbsent.getContext().getResources().getColor(android.R.color.white));
                holder.btnAlive.setBackgroundResource(R.drawable.shape_blue_blank);
                holder.btnAlive.setTextColor(holder.btnAbsent.getContext().getResources().getColor(R.color.colorAccent));
                onSwitchSelectListener.switched(studentItem, false);
            }
        });
        holder.btnAlive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnAlive.setBackgroundResource(R.drawable.shape_blue_fill);
                holder.btnAlive.setTextColor(holder.btnAbsent.getContext().getResources().getColor(android.R.color.white));
                holder.btnAbsent.setBackgroundResource(R.drawable.shape_blue_blank);
                holder.btnAbsent.setTextColor(holder.btnAbsent.getContext().getResources().getColor(R.color.colorAccent));
                onSwitchSelectListener.switched(studentItem, true);

            }
        });
        Glide.with(holder.txtTitle.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(studentItem.getImageUrl())
                .into(holder.imgProfile);

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtName)
        TextView txtTitle;

        @BindView(R.id.profile_image)
        ImageView imgProfile;

        @BindView(R.id.btnAbsent)
        Button btnAbsent;

        @BindView(R.id.btnAlive)
        Button btnAlive;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
