package com.twitter.demo.ui.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.utilities.BaseViewHolder;
import com.twitter.demo.utilities.PicassoCache;
import com.twitter.demo.utilities.RecyclerViewItemClickListener;
import com.twitter.sdk.android.core.models.User;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bassem Qoulta (Deda) on  7/13/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
/**
 * this class is Followers adapter used for setting the followers items
 */

public class FollowersAdapter extends GenericAdapterRecyclerView<User, FollowersAdapter.FollowerViewHolder> {

    private RecyclerViewItemClickListener<User> listener;

    public FollowersAdapter(List<User> Users, RecyclerViewItemClickListener<User> clickListener) {
        super(Users);
        this.listener = clickListener;
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item_list, parent, false);
        return new FollowerViewHolder(view, parent.getContext());
    }

    @Override
    public void onViewRecycled(FollowerViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder viewHolder, int position) {
        User currentItem = itemsArrayList.get(position);
        viewHolder.bind(currentItem, this.listener);
    }

    public void removeAll() {
        if(!itemsArrayList.isEmpty()){
            itemsArrayList.clear();
            notifyDataSetChanged();
        }
    }

    static class FollowerViewHolder extends BaseViewHolder<User> {
        private WeakReference<Context> weakReference;
        private User userDataModel;
        RecyclerViewItemClickListener<User> listener;

        @BindView(R.id.iv_profile)
        ImageView ivProfile;
        @BindView(R.id.tv_full_name)
        TextView tvFullName;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_bio)
        TextView tvBio;
        @BindView(R.id.card_view)
        RelativeLayout cardView;

        FollowerViewHolder(View itemView, Context context) {
            super(itemView);
            weakReference = new WeakReference<Context>(context);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(User userDataModel, RecyclerViewItemClickListener<User> clickListener) {
            this.userDataModel = userDataModel;
            this.listener = clickListener;
            setUserName(userDataModel.name);
            setUserLocation(userDataModel.location);
            setUserBio(userDataModel.description);
            setUserImage(userDataModel.profileImageUrl);

        }

        private void setUserBio(String description) {
            if (!TextUtils.isEmpty(description)) {
                tvBio.setText(description);
            } else {
                tvBio.setVisibility(View.GONE);
            }
        }

        private void setUserLocation(String location) {
            if (!TextUtils.isEmpty(location)) {
                tvLocation.setText(location);
            } else {
                tvLocation.setVisibility(View.GONE);
            }
        }

        private void setUserImage(String profileImageUrl) {
            PicassoCache.getPicassoInstance(weakReference.get())
                    .load(profileImageUrl)
                    .placeholder(R.drawable.avatar)
                    .into(ivProfile);
        }

        private void setUserName(String name) {
            if (!TextUtils.isEmpty(name)) {
                tvFullName.setText(name);
            } else {
                tvFullName.setVisibility(View.GONE);
            }
        }


        @OnClick(R.id.card_view)
        public void openFollowerDetails() {
            if(this.listener != null){
                this.listener.onItemClicked(getAdapterPosition(), this.userDataModel );
            }
        }
    }
}