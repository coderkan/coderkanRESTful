package com.eguzeler.rest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eguzeler.rest.R;
import com.eguzeler.rest.converters.UserDetail;
import com.eguzeler.rest.presenters.UserDetailInfoPresenter;
import com.eguzeler.rest.view.UserDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserDetailnfoFragment extends Fragment implements UserDetailView {

    private static final String ID_ARGS = "id";

    private String mUserId;

    private OnFragmentInteractionListener mListener;
    private UserDetailInfoPresenter mUserDetailPresenter = null;

    @BindView(R.id.name_tv)
    TextView mNameTextView;

    @BindView(R.id.job_tv)
    TextView mJobTextView;

    @BindView(R.id.phone_etv)
    EditText mPhoneEditText;

    @BindView(R.id.address_etv)
    EditText mAddressEditText;

    @BindView(R.id.big_avatar_im)
    ImageView mAvatar;

    public UserDetailnfoFragment() {
    }

    public static UserDetailnfoFragment newInstance(String id) {
        UserDetailnfoFragment fragment = new UserDetailnfoFragment();
        Bundle args = new Bundle();
        args.putString(ID_ARGS, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserId = getArguments().getString(ID_ARGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detailnfo, container, false);
        ButterKnife.bind(this, view);
        if(mUserDetailPresenter == null){
            mUserDetailPresenter = new UserDetailInfoPresenter(getActivity().getApplicationContext(), this);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mUserDetailPresenter != null)
            mUserDetailPresenter.loadUserDetailInfo(mUserId);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLoadUserDetail(UserDetail userDetail) {
        if(userDetail == null)
            return;

        mNameTextView.setText(userDetail.getFullName());
        mJobTextView.setText(userDetail.getJob());
        mPhoneEditText.setText(userDetail.getPhone());
        mAddressEditText.setText(userDetail.getAddress());
        Glide.with(getActivity())
                .load(userDetail.getUserAvatar())
                .into(mAvatar);

    }

    @OnClick(R.id.save_fab) void saveUserDetailClick(){
        mUserDetailPresenter.updateUserInfo(mUserId, mPhoneEditText.getText().toString(), mAddressEditText.getText().toString());
    }

    @Override
    public void onLoadError(String err) {
        Toast.makeText(getActivity(),err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateMessage(String msg) {
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
