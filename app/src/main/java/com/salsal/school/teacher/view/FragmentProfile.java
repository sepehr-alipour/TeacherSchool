package com.salsal.school.teacher.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.salsal.school.teacher.R;
import com.salsal.school.teacher.model.TeacherProfileRes;

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

public class FragmentProfile extends Fragment {


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
        adapter.addFrag(FragmentProfileDetail.newInstance("", ""), "مشخصات کلی");
        adapter.addFrag(FragmentProfileSchedule.newInstance("", ""), "برنامه هفتگی");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
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
