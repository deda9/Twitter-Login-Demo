package com.twitter.demo.ui.adapters;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bassem.anwer on 7/20/16.
 * Bassem Qoulta (Deda9).
 * https://eg.linkedin.com/in/bassemqoulta
 */
public abstract class GenericAdapterRecyclerView<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    List<T> itemsArrayList;

    public GenericAdapterRecyclerView() {
        if (itemsArrayList == null)
            itemsArrayList = new ArrayList<>();
    }

    public GenericAdapterRecyclerView(final List<T> objects) {
        if (itemsArrayList == null)
            itemsArrayList = new ArrayList<>();
        itemsArrayList.addAll(objects);
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    public void add(final T object) {
        itemsArrayList.add(object);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<T> objects) {
        for (T object : objects) {
            itemsArrayList.add(object);
        }
        notifyItemRangeInserted(itemsArrayList.size(), objects.size());
    }

    /**
     * Remove all elements from the list.
     */
    public void clear() {
        final int size = getItemCount();
        itemsArrayList.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemCount() {
        if (itemsArrayList == null) return 0;
        return itemsArrayList.size();
    }

    public T getItem(final int position) {
        return itemsArrayList.get(position);
    }

    public long getItemId(final int position) {
        return position;
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     * @return The position of the specified item.
     */
    public int getPosition(final T item) {
        return itemsArrayList.indexOf(item);
    }

    /**
     * Inserts the specified object at the specified index in the array.
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    public void insert(final T object, int index) {
        itemsArrayList.add(index, object);
        notifyItemInserted(index);
    }

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     */
    public void remove(T object) {
        final int position = getPosition(object);
        itemsArrayList.remove(object);
        notifyItemRemoved(position);
    }

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained in this adapter.
     */
    public void sort(Comparator<? super T> comparator) {
        Collections.sort(itemsArrayList, comparator);
        notifyItemRangeChanged(0, getItemCount());
    }

    public List<T> getItemsArrayList() {
        return itemsArrayList;
    }
}