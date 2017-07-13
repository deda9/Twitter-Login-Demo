package com.twitter.demo.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Bassem Qoulta (Deda) on  7/13/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(final T t, final RecyclerViewItemClickListener<T> clickListener);
}
