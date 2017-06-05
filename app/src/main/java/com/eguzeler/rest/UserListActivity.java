package com.eguzeler.rest;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.eguzeler.rest.adapters.UserListAdapter;
import com.eguzeler.rest.converters.User;
import com.eguzeler.rest.fragments.UserDetailnfoFragment;
import com.eguzeler.rest.listeners.RecyclerItemClickListener;
import com.eguzeler.rest.presenters.UserListPresenter;
import com.eguzeler.rest.view.UserListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListActivity extends AppCompatActivity implements UserListView , UserDetailnfoFragment.OnFragmentInteractionListener{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private List<User> mUserList = new ArrayList<>();
    private UserListPresenter mUserListPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadAdapter();

        if(mUserListPresenter == null)
            mUserListPresenter = new UserListPresenter(this);
        mUserListPresenter.loadUserLists();

    }

    private void loadAdapter() {

        final RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mAdapter = new UserListAdapter(this.mUserList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                User user = mUserList.get(position);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, UserDetailnfoFragment.newInstance(String.valueOf(user.getId())),"USERDETAILINFO")
                        .addToBackStack(null)
                        .commit();
            }
        }));
    }

    @Override
    public void onLoadUserList(List<User> list) {
        this.mUserList.clear();
        this.mUserList.addAll(list);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadError(String msg) {
        Toast.makeText(UserListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
