package com.github.booknara.appcategory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class BaseAppArrayAdapter extends ArrayAdapter<Item> {
    /**
     * Current context
     */
    protected Context mContext;
    protected List<Item> mOriginalPackages;
    protected List<Item> mPackages;
    protected LayoutInflater mInflater;

    /**
     * Default layout used for each row
     */

    // -------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------

    /**
     * Constructor
     *
     * @param context The current context.
     * @param packages   The packages to represent in the ListView.
     */
    public BaseAppArrayAdapter(Context context, List<Item> packages) {
        super(context, 0, packages);
        mContext = context;
        mOriginalPackages = packages;
        mPackages = packages;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void add(Item item) {
        mPackages.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).getViewType() != RowType.HEADER_ITEM.ordinal();

    }
    
    @Override
    public int getCount() {
    	return mPackages.size();
    }
    
    public Context getContext() {
    	return mContext;
    }

}