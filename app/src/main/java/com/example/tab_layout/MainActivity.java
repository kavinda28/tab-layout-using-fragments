package com.example.tab_layout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tablayout_id);
        viewPager=findViewById(R.id.view_pager);

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Tab 1");
        arrayList.add("Tab 2");
        arrayList.add("Tab 3");

        prepareViewpager(viewPager,arrayList);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void prepareViewpager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adpter=new MainAdapter(getSupportFragmentManager());

        mainFragment  fragment=new mainFragment();
        for (int i=0;i<arrayList.size(); i++){
            Bundle bundle=new Bundle();
            bundle.putString("title",arrayList.get(i));
            fragment.setArguments(bundle);
            adpter.addFragment(fragment,arrayList.get(i));
            fragment=new mainFragment();

        }
        viewPager.setAdapter(adpter);
    }

    private class MainAdapter extends FragmentPagerAdapter {
ArrayList<String> arrayList=new ArrayList<>();
List<Fragment> fragmentList=new ArrayList<>();

public void addFragment(Fragment fragment,String title){
arrayList.add(title);
fragmentList.add(fragment);

}

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }
}
