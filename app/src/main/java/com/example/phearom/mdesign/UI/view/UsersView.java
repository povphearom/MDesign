package com.example.phearom.mdesign.UI.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phearom.mdesign.BR;
import com.example.phearom.mdesign.R;
import com.example.phearom.mdesign.UI.binder.UserBinder;
import com.example.phearom.mdesign.UI.utils.ServerUtil;
import com.example.phearom.mdesign.UI.viewmodel.UserViewModel;
import com.example.phearom.mdesign.UI.viewmodel.UsersViewModel;
import com.example.phearom.mdesign.adapter.binder.CompositeItemBinder;
import com.example.phearom.mdesign.adapter.binder.ItemBinder;
import com.example.phearom.mdesign.databinding.UsersViewBinding;
import com.example.phearom.mdesign.listener.ClickHandler;
import com.example.phearom.mdesign.listener.LongClickHandler;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsersView extends AppCompatActivity {
    private UsersViewModel usersViewModel;
    private UsersViewBinding mBinding;

    private void setLoading(int loading) {
        if (null == mBinding) return;

        switch (loading){
            case 0:
                mBinding.activityUsersProgressbar.setVisibility(View.VISIBLE);
                mBinding.contentError.setVisibility(View.GONE);
                break;
            case 1:
                mBinding.activityUsersProgressbar.setVisibility(View.GONE);
                mBinding.contentError.setVisibility(View.GONE);
                break;

            case 2:
                mBinding.activityUsersProgressbar.setVisibility(View.GONE);
                mBinding.contentError.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersViewModel = new UsersViewModel();

        mBinding = DataBindingUtil.setContentView(this, R.layout.users_view);
        mBinding.setUsersViewModel(usersViewModel);
        mBinding.setView(this);
        mBinding.activityUsersRecycler.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(mBinding.toolbar);

        loadData();
    }

    public void reload(View v){
        loadData();
    }

    public void loadData() {
        setLoading(0);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServerUtil.URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            Log.i("Data", result);
                            JSONObject data = new JSONObject(result);
                            JSONArray array = data.getJSONArray("data");
                            if (array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject json = array.getJSONObject(i);
                                    int rank = json.getInt("id");
                                    String country = json.getJSONObject("photo_metadata").getString("title");
                                    String population = json.getJSONObject("photo_owner").getString("owner");
                                    String flag = json.getString("photo_physical");
                                    usersViewModel.addUser(String.valueOf(rank), population, country, flag);
                                }
                                setLoading(1);
                            } else {
                                setLoading(2);
                            }
                        } catch (Exception e1) {
                            setLoading(2);
                            e1.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(2);
            }
        });
        queue.add(stringRequest);
    }

    public ClickHandler<UserViewModel> clickHandler() {
        return new ClickHandler<UserViewModel>() {
            @Override
            public void onClick(UserViewModel user) {
                Toast.makeText(UsersView.this, user.getCountry() + " : " + user.getPopulation(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public LongClickHandler<UserViewModel> longClickHandler() {
        return new LongClickHandler<UserViewModel>() {
            @Override
            public void onLongClick(UserViewModel user) {
                Toast.makeText(UsersView.this, "LONG CLICK: " + user.getCountry() + " " + user.getPopulation(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public ItemBinder<UserViewModel> itemViewBinder() {
//        new SuperUserBinder(BR.user, R.layout.item_super_user),
        return new CompositeItemBinder<UserViewModel>(
                new UserBinder(BR.user, R.layout.item_user)
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Ion.getDefault(this).getBitmapCache().clear();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Ion.getDefault(this).cancelAll();
    }
}