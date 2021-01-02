package com.example.c_fest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class Pageradapter extends FragmentPagerAdapter {

   int NoOfTabs;

   public Pageradapter(FragmentManager fm,int NumberOfTabs)
   {
       super(fm);
        this.NoOfTabs=NumberOfTabs;
   }




    @Override
    public Fragment getItem(int Position) {

       switch (Position)
       {
           case 0:
               Home_tab tab1 = new Home_tab();
                       return tab1;
           case 1:
               Event_tab tab2 = new Event_tab();
               return tab2;
           case 2:
               Setting_tab tab3 = new Setting_tab();
               return tab3;
               default:
                   return null;
       }
    }

    @Override
    public int getCount() {
       return NoOfTabs;
    }
}
