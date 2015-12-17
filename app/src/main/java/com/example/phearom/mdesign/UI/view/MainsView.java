package com.example.phearom.mdesign.UI.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.phearom.mdesign.R;
import com.example.phearom.mdesign.UI.model.FileInfo;
import com.example.phearom.mdesign.UI.viewmodel.MainsViewModel;
import com.example.phearom.mdesign.UI.viewmodel.SuperMainViewModel;
import com.example.phearom.mdesign.adapter.MyAdapter;
import com.example.phearom.mdesign.databinding.ActivityMainBinding;

public class MainsView extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private int[] myDataImage ={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6};
    private MainsViewModel mainsViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mainsViewModel = new MainsViewModel();
        mainsViewModel.files.add(new SuperMainViewModel(new FileInfo("Android", R.drawable.p1)));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainsViewModel(mainsViewModel);
//        binding.setView(this);
        binding.activityUsersRecycler.setLayoutManager(new LinearLayoutManager(this));
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

//    public ItemBinder<MainViewModel> itemViewBinder()
//    {
//        return new CompositeItemBinder<MainViewModel>(
//                new SuperMainBinder(BR.file, R.layout.item_super_main),
//                new MainBinder(BR.file, R.layout.item_main)
//        );
//    }
}
