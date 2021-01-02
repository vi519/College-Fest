package com.example.c_fest;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity  implements Home_tab.OnFragmentInteractionListener,Event_tab.OnFragmentInteractionListener,Setting_tab.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.Tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_black_24dp).setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_event_black_24dp).setText("EVENT"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_settings_black_24dp).setText("SETTING"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.page);
        final Pageradapter adapter = new Pageradapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
