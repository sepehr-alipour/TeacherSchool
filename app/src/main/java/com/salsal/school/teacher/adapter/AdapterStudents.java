package com.salsal.school.teacher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.salsal.school.teacher.R;
import com.salsal.school.teacher.model.StudentRes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class AdapterStudents extends RecyclerView.Adapter<AdapterStudents.ViewHolder> {
    private final List<StudentRes.DataBean> studentList;

    @Override
    public AdapterStudents.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student, parent, false);
        return new ViewHolder(view);
    }

    public AdapterStudents(List<StudentRes.DataBean> studentRes) {
        this.studentList = studentRes;
    }

    @Override
    public void onBindViewHolder(AdapterStudents.ViewHolder holder, int position) {
        StudentRes.DataBean studentItem = studentList.get(position);
        holder.txtTitle.setText(studentItem.getName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_action_profile);
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


        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
