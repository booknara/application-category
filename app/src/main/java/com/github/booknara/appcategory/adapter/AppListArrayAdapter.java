package com.github.booknara.appcategory.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.booknara.appcategory.BuildConfig;
import com.github.booknara.appcategory.util.ExceptionUtil;
import com.github.booknara.appcategory.util.Logger;

import java.util.List;


public class AppListArrayAdapter extends BaseAppArrayAdapter {
	private static final String CNAME = AppListArrayAdapter.class.getSimpleName();
	
	public AppListArrayAdapter(Context context, List<Item> packages) {
		super(context, packages);
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		try {
            convertView = mPackages.get(position).getView(mInflater, convertView, this, position);
		} catch(Exception e) {
			if(BuildConfig.DEBUG)
				Logger.e(CNAME, ExceptionUtil.exception(e));
		}
        
        return convertView;
    }
}