package com.example.phearom.mdesign.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phearom.mdesign.R;
import com.example.phearom.mdesign.databinding.ItemMainBinding;
import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final int[] mDataImage;
    private final Context mContext;
    private int lastPosition = -1;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
//            mTextView = (TextView) v.findViewById(R.id.info_text);
//            mImageView = (ImageView) v.findViewById(R.id.info_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context ctx,int[] myDataImage) {
        mContext = ctx;
        mDataImage = myDataImage;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_main, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        ItemMainBinding binding = DataBindingUtil.bind(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText("Pretty Girl " + (position + 1));
        int rad = mDataImage.length -1;
        int index = (int)(Math.random() * rad);
        Log.i("index", ""+index);
        Picasso.with(mContext).load(mDataImage[index]).centerCrop().fit().into(holder.mImageView);
        setAnimation((View) holder.mImageView.getParent(), position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_in_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return 1000;
    }
}