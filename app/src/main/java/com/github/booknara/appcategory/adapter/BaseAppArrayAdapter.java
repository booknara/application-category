package com.github.booknara.appcategory.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;


import com.github.booknara.appcategory.vo.PackageVO;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class BaseAppArrayAdapter extends ArrayAdapter<Item> {
    /**
     * Current context
     */
    protected Context mContext;
    protected List<Item> mOriginalPackages;
    protected List<Item> mPackages;
    protected SparseArray<View> mViewArray;
    protected LayoutInflater mInflater;

    private SparseBooleanArray mSelectedItemsIds;

    private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();
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
    	this.mViewArray = new SparseArray<View>(packages.size());
        
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mSelectedItemsIds = new SparseBooleanArray();
    }

    public void add(Item item) {
        mPackages.add(item);
        notifyDataSetChanged();
    }

    public void add(int index, Item item) {
        mPackages.add(index, item);
        notifyDataSetChanged();
    }

    public void remove(String packageName) {
        for(Item item: mPackages) {
            if (item instanceof HeaderItem)
                continue;

            if (item instanceof AppListItem) {
                if (((AppListItem)item).vo.pname.equalsIgnoreCase(packageName)) {
                    mPackages.remove(item);
                    break;
                }
            }

        }

        notifyDataSetChanged();
    }

    public void remove(PackageVO item) {
        String packageName = item.pname;
        remove(packageName);
    }

    public void remove(String packageName, long uninstalledDate) {
        for(Item item: mPackages) {
            if (item instanceof HeaderItem)
                continue;

            if (item instanceof AppListItem) {
                if (((AppListItem)item).vo.pname.equalsIgnoreCase(packageName) && ((AppListItem)item).vo.uninstalledDate == uninstalledDate) {
                    mPackages.remove(item);
                    break;
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        mViewArray.clear();
        super.notifyDataSetChanged();
    }

    public void replaceAll(List<Item> items) {
        clear();

        super.addAll(items);
        notifyDataSetChanged();
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

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public void setNewSelection(int position, boolean value) {
        mSelection.put(position, value);
        notifyDataSetChanged();
    }

    public boolean isPositionChecked(int position) {
        Boolean result = mSelection.get(position);
        return result == null ? false : result;
    }

    public Set<Integer> getCurrentCheckedPosition() {
        return mSelection.keySet();
    }

    public void removeSelection(int position) {
        mSelection.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }


}