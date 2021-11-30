package com.example.splitisfun;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

//this activity is initaited if the user clicks on any group while on the GroupList activity.
public class HandleOnGroupClickActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handle_on_group_click_activity);

        /* get extra data(name of the group the user clicked on) from the intent that started this activity
         * Hence, we can load all the members and bills of the group the user clicked on in GroupList Activity*/
        Intent intent = getIntent();
        String gName = intent.getStringExtra(GroupListActivity.EXTRA_TEXT_GNAME);

        TabLayout tabLayout = findViewById(R.id.tablayout_id);
        ViewPager viewPager = findViewById(R.id.viewpager_id);

        //set toolbar
        Toolbar toolbar = findViewById(R.id.handleOnGroupClickToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setElevation(0);
        }
        setTitle(gName);

        //create adapter for viewpager and add all three fragments/tabs to this adapter
        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
        //pass along the group name so that the fragment can generate all the members of the group that user clicked on GroupList activity.
        adapter.addFragment(MembersTabFragment.newInstance(gName), "Members");
        //pass along the group name so that the fragment can generate all the expenses of the group that user clicked on GroupList activity.
        adapter.addFragment(ExpensesTabFragment.newInstance(gName), "Expenses");
        //pass along the group name so that the fragment can generate all the balances of the group that user clicked on GroupList activity.
        adapter.addFragment(BalancesTabFragment.newInstance(gName), "Balances");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            //if user clicks on back button initiates finish to close activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}





















