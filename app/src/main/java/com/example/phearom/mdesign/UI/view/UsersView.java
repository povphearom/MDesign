package com.example.phearom.mdesign.UI.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class UsersView extends Fragment {
    private UsersViewModel usersViewModel;
    private UsersViewBinding mBinding;

    private void setLoading(int loading) {
        if (null == mBinding) return;

        switch (loading) {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        usersViewModel = new UsersViewModel();

        mBinding = DataBindingUtil.inflate(inflater, R.layout.users_view, container, false);
        mBinding.setUsersViewModel(usersViewModel);
        mBinding.setView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mBinding.activityUsersRecycler.setLayoutManager(layoutManager);

        loadData();
        return mBinding.getRoot();
    }

    public void reload(View v) {
        loadData();
    }

    public void loadData() {
        setLoading(0);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
                Toast.makeText(getActivity(), user.getCountry() + " : " + user.getPopulation(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public LongClickHandler<UserViewModel> longClickHandler() {
        return new LongClickHandler<UserViewModel>() {
            @Override
            public void onLongClick(UserViewModel user) {
                Toast.makeText(getActivity(), "LONG CLICK: " + user.getCountry() + " " + user.getPopulation(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public ItemBinder<UserViewModel> itemViewBinder() {
        return new CompositeItemBinder<UserViewModel>(
                new UserBinder(BR.user, R.layout.item_user)
        );
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Ion.getDefault(getActivity()).getBitmapCache().clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        Ion.getDefault(getActivity()).cancelAll();
    }
}