package com.when.threemb.when;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class OtherDays extends AppCompatActivity {
    public static SharedPreferences asp,aspt,acheck;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


  public ArrayList<Earthquake> thirdfunction(String days,int dow)
  {
      /*Bundle kuchbhi=getIntent().getExtras();
      int dow;
      if(kuchbhi==null)
          dow=-1;
      else{
          dow=kuchbhi.getInt("Day",-1);
      }*/


      ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(asp,aspt,acheck,dow);
        /*timetableListView = (ListView) findViewById(R.id.list);
        adapter = new TimetableAdapter(this, 0, periods);
        timetableListView.setAdapter(adapter);*/
      return earthquakes;

  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_days);

        asp = getSharedPreferences("attended", Context.MODE_PRIVATE);
        aspt = getSharedPreferences("totalclass", Context.MODE_PRIVATE);
        acheck=getSharedPreferences("kyanaamdu", Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // BACK BUTTON
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_other_days, container, false);

            EarthquakeAdapter adapter;
            ListView timetableListView;
            String days="";
            int dow=getArguments().getInt(ARG_SECTION_NUMBER);

            OtherDays object=new OtherDays();
            switch(dow)
            {
                case 1:days="Monday";break;
                case 2:days="Tuesday";break;
                case 3:days="Wednesday";break;
                case 4:days="Thursday";break;
                case 5:days="Friday";break;
                default:days="Monday";
            }
            ArrayList<Earthquake> periods=object.thirdfunction(days,dow);
            timetableListView = (ListView) rootView.findViewById(R.id.list);
            for(int i=0;i<periods.size();i++)
            {
                Earthquake obj=periods.get(i);
                obj.setmStatus("0");
                periods.set(i,obj);
            }
            adapter = new EarthquakeAdapter(getContext(), 0, periods);
            timetableListView.setAdapter(adapter);

           /* TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
            return rootView;
        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Mon";
                case 1:
                    return "Tue";
                case 2:
                    return "Wed";
                case 3:
                    return "Thurs";
                case 4:
                    return "Fri";
            }
            return null;
        }
    }
}
