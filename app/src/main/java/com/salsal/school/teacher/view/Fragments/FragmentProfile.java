package com.salsal.school.teacher.view.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salsal.school.teacher.R;
import com.salsal.school.teacher.model.TeacherProfileRes;
import com.salsal.school.teacher.view.Activities.ActivityEditProfile;
import com.salsal.school.teacher.view.Activities.ActivityLogin;
import com.salsal.school.teacher.view.Activities.ActivitySchools;
import com.salsal.school.teacher.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

/**
 * Created by Taraabar on 12/16/2016.
 */

public class FragmentProfile extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.htab_maincontent)
    CoordinatorLayout htabMaincontent;
    Unbinder unbinder;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fabEdit)
    FloatingActionButton fabEdit;

    public void dataLoaded(Response<TeacherProfileRes> response) {
        txtName.setText(response.body().getData().getName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_action_profile);
        Glide.with(getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(response.body().getData().getImageUrl())
                .into(profileImage);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        unbinder = ButterKnife.bind(this, view);
        initInstances();
        setupViewPager(viewPager);
        fabEdit.setOnClickListener(this);
        return view;
    }

    public static FragmentProfile newInstance(String param1) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        args.putString("param", param1);
        fragment.setArguments(args);
        return fragment;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(FragmentProfileDetail.newInstance("", ""), "???????????? ??????");
        adapter.addFrag(FragmentProfileSchedule.newInstance("", ""), "???????????? ??????????");
        adapter.addFrag(FragmentAddSchool.newInstance("", ""), "???????? ??????????");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        fabEdit.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_edit));
                        if (!fabEdit.isShown())
                            fabEdit.show();
                        break;
                    case 1:
                        fabEdit.hide();

                        break;
                    case 2:
                        fabEdit.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_add));
                        if (!fabEdit.isShown())
                            fabEdit.show();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initInstances() {
        collapsingToolbarLayout.setTitleEnabled(false);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (viewPager.getCurrentItem()) {
            case 0:
                startActivity(new Intent(getContext(), ActivityEditProfile.class));

                break;
            case 2:

                Intent intent = new Intent(getContext(), ActivitySchools.class);
                startActivity(intent);
                break;

        }
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
