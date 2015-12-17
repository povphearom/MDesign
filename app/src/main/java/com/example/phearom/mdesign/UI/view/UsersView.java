package com.example.phearom.mdesign.UI.view;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.phearom.mdesign.BR;
import com.example.phearom.mdesign.R;
import com.example.phearom.mdesign.UI.binder.SuperUserBinder;
import com.example.phearom.mdesign.UI.binder.UserBinder;
import com.example.phearom.mdesign.UI.model.User;
import com.example.phearom.mdesign.UI.viewmodel.UserViewModel;
import com.example.phearom.mdesign.UI.viewmodel.UsersViewModel;
import com.example.phearom.mdesign.adapter.binder.CompositeItemBinder;
import com.example.phearom.mdesign.adapter.binder.ItemBinder;
import com.example.phearom.mdesign.databinding.UsersViewBinding;
import com.example.phearom.mdesign.listener.ClickHandler;
import com.example.phearom.mdesign.listener.LongClickHandler;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsersView extends AppCompatActivity
{
//    private String URL_SERVER = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    private String URL_SERVER = "https://raw.githubusercontent.com/abhijaju/Test-Collection-for-Image-Search-Result-Diversity-in-Flickr/master/queries/argos/query_data.json";

    private UsersViewModel usersViewModel;
    private UsersViewBinding binding;

//    private int[] mImages = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        usersViewModel = new UsersViewModel();
//        usersViewModel.users.add(new SuperUserViewModel(new User("Android", R.drawable.p2)));

        binding = DataBindingUtil.setContentView(this, R.layout.users_view);
        binding.setUsersViewModel(usersViewModel);
        binding.setView(this);
        binding.activityUsersRecycler.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    private void loadData(){
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) binding.getView().findViewById(R.id.swl_Loading);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.MAGENTA, Color.BLACK, Color.CYAN, Color.DKGRAY, Color.LTGRAY);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setEnabled(true);
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        Ion.with(this).load(URL_SERVER).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                try {
                    Log.i("Data", result);
                    JSONObject data = new JSONObject(result);
                    JSONArray array = data.getJSONArray("data");
                    if (array.length() > 0){
                        for (int i=0 ;i< array.length();i++){
                            JSONObject json = array.getJSONObject(i);
                            int rank = json.getInt("id");
                            String country= json.getJSONObject("photo_metadata").getString("title");
                            String population= json.getJSONObject("photo_owner").getString("owner");
                            String flag = json.getString("photo_physical");
                            usersViewModel.users.add(new UserViewModel(new User(String.valueOf(rank),population,country,flag)));
                        }
                    }
                    swipeRefreshLayout.setEnabled(false);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e1) {
                    swipeRefreshLayout.setEnabled(false);
                    swipeRefreshLayout.setRefreshing(false);
                    e1.printStackTrace();
                }
            }
        });
    }

    public ClickHandler<UserViewModel> clickHandler()
    {
        return new ClickHandler<UserViewModel>()
        {
            @Override
            public void onClick(UserViewModel user)
            {
                Toast.makeText(UsersView.this, user.getCountry() + " : " + user.getPopulation(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public LongClickHandler<UserViewModel> longClickHandler()
    {
        return new LongClickHandler<UserViewModel>()
        {
            @Override
            public void onLongClick(UserViewModel user)
            {
                Toast.makeText(UsersView.this, "LONG CLICK: " + user.getCountry() + " " + user.getPopulation(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public ItemBinder<UserViewModel> itemViewBinder()
    {
        return new CompositeItemBinder<UserViewModel>(
                new SuperUserBinder(BR.user, R.layout.item_super_user),
                new UserBinder(BR.user, R.layout.item_user)
        );
    }
}