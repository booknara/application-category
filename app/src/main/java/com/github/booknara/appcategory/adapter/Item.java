package com.github.booknara.appcategory.adapter;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Daehee Han on 4/22/14.
 */
public interface Item {
    int getViewType();
    View getView(LayoutInflater inflater, View convertView, BaseAppArrayAdapter adapter, int position);
}
