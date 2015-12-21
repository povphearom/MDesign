package com.example.phearom.mdesign.UI.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.phearom.mdesign.R;
import com.example.phearom.mdesign.databinding.MainViewBinding;

public class MainView extends AppCompatActivity {

    private MainViewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.main_view);
        setSupportActionBar(mBinding.toolbar);

        ViewPager viewPager = mBinding.contentMain.viewpager;
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                this));

        TabLayout tabLayout = mBinding.slidingTabs;
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = mBinding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snakeBar(view);
            }
        });
    }

    private void snakeBar(View view) {
        Snackbar snackbar = Snackbar
                .make(view, "Open User View", Snackbar.LENGTH_LONG)
                .setAction("Open", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainView.this, UsersView.class));
                    }
                })
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}