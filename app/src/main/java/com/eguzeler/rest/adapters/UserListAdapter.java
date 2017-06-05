package com.eguzeler.rest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eguzeler.rest.R;
import com.eguzeler.rest.converters.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 03.06.2017.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    private List<User> mUserDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_and_lastname_tv)
        TextView mFullName;

        @BindView(R.id.job_tv)
        TextView mJob;

        @BindView(R.id.user_avatar_im)
        ImageView mAvatar;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void add(int position, User item) {
        mUserDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mUserDataset.indexOf(item);
        mUserDataset.remove(position);
        notifyItemRemoved(position);
    }

    public UserListAdapter(List<User> myDataset){
        this.mUserDataset = myDataset;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.custom_user_info;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        UserListAdapter.ViewHolder vh = new UserListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ViewHolder holder, int position) {
        if(holder == null)
            return;

        User user = this.mUserDataset.get(position);
        holder.mFullName.setText(user.getFullName());
        holder.mJob.setText(user.getJob());

        Glide.with(context)
                .load(user.getUserAvatar())
                .into(holder.mAvatar);

    }

    @Override
    public int getItemCount() {
        return mUserDataset.size();
    }
}
