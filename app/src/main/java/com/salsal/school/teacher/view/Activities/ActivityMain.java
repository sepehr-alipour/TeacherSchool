package com.salsal.school.teacher.view.Activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.salsal.school.teacher.R;
import com.salsal.school.teacher.view.BaseActivity;
import com.salsal.school.teacher.view.Fragments.FragmentNotifications;
import com.salsal.school.teacher.view.Fragments.FragmentProfile;
import com.salsal.school.teacher.view.Fragments.FragmentStudents;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityMain extends BaseActivity implements View.OnClickListener,
        AHBottomNavigation.OnTabSelectedListener

{

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private final int TAB_PROFILE = 0;
    private final int TAB_STUDENTS = 1;
    private final int TAB_NOTIFICATIONS = 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_profile, R.drawable.ic_action_profile, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_students, R.drawable.ic_action_student, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_notifications, R.drawable.ic_action_notification, R.color.colorPrimary);

// Add items
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setOnTabSelectedListener(this);
        bottomNavigation.setCurrentItem(TAB_NOTIFICATIONS);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        Fragment selectedFragment = null;
        switch (position) {
            case TAB_PROFILE:
                toolbar.setVisibility(View.GONE);
                selectedFragment = FragmentProfile.newInstance("");
                break;
            case TAB_STUDENTS:
                toolbar.setVisibility(View.VISIBLE);

                selectedFragment = FragmentStudents.newInstance("", "");
                break;
            case TAB_NOTIFICATIONS:
                toolbar.setVisibility(View.VISIBLE);

                selectedFragment = FragmentNotifications.newInstance("", "");
                break;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homeContainer, selectedFragment);
        transaction.commit();
        return true;
    }
}
