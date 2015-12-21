package com.example.phearom.mdesign.UI.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phearom.mdesign.R;
import com.example.phearom.mdesign.databinding.PageFragmentBinding;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PageFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.page_fragment, container, false);
        TextView textView = binding.textPage;
        textView.setText("Fragment #" + mPage);

        return binding.getRoot();
    }
}