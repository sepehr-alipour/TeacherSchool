package com.salsal.school.teacher.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.OnStudentAttendencedListener;
import com.salsal.school.teacher.model.StudentRes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sepehr on 12/4/2017.
 */

public class AdapterAttendance extends RecyclerView.Adapter<AdapterAttendance.ViewHolder> {
    private final List<StudentRes.DataBean> studentList;
    private final OnStudentAttendencedListener onSwitchSelectListener;

    @Override
    public AdapterAttendance.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attendance, parent, false);
        return new ViewHolder(view);
    }

    public AdapterAttendance(List<StudentRes.DataBean> studentRes, OnStudentAttendencedListener listener) {
        this.studentList = studentRes;
        this.onSwitchSelectListener = listener;
    }

    @Override
    public void onBindViewHolder(final AdapterAttendance.ViewHolder holder, int position) {
        final StudentRes.DataBean studentItem = studentList.get(position);
        holder.txtTitle.setText(studentItem.getName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_action_profile);
        setOnclick(holder.btnAbsent, studentItem);
        setOnclick(holder.btnAlive, studentItem);
        setOnclick(holder.btnLate, studentItem);
        Glide.with(holder.txtTitle.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(studentItem.getImageUrl())
                .into(holder.imgProfile);

    }

    private void changeButtonStyle(Button btn) {
        btn.setBackgroundResource(R.drawable.shape_blue_fill);
        btn.setTextColor(btn.getContext().getResources().getColor(android.R.color.white));
        LinearLayout parent = (LinearLayout) btn.getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChildAt(i).getId() != btn.getId()) {
                if (parent.getChildAt(i) instanceof Button) {
                    parent.getChildAt(i).setBackgroundResource(R.drawable.shape_blue_blank);
                    ((Button) parent.getChildAt(i)).setTextColor(btn.getContext().getResources().getColor(R.color.colorAccent));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    private void setOnclick(final Button btn, final StudentRes.DataBean studentItem) {
        switch (btn.getId()) {
            case R.id.btnAlive:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeButtonStyle(btn);
                        onSwitchSelectListener.switched(studentItem, true, "0");
                    }
                });

                break;
            case R.id.btnLate:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeButtonStyle(btn);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(btn.getContext());
                        View dialogLate = LayoutInflater.from(btn.getContext()).inflate(R.layout.dialog_attendance_late, null);
                        final AppCompatEditText edtTime = dialogLate.findViewById(R.id.edtTime);
                        dialog.setView(dialogLate);
                        dialog.setPositiveButton(R.string.btn_accept, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                onSwitchSelectListener.switched(studentItem, false, edtTime.getText().toString());
                            }
                        });
                        dialog.show();


                    }
                });
                break;
            case R.id.btnAbsent:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeButtonStyle(btn);
                        onSwitchSelectListener.switched(studentItem, false, "0");
                    }
                });

                break;
        }


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

        @BindView(R.id.btnLate)
        Button btnLate;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
