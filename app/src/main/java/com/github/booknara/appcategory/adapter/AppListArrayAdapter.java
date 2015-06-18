package com.github.booknara.appcategory.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.booknara.appcategory.BuildConfig;
import com.github.booknara.appcategory.util.ExceptionUtil;
import com.github.booknara.appcategory.util.Logger;
import com.github.booknara.appcategory.vo.PackageVO;

import java.util.ArrayList;
import java.util.List;


public class AppListArrayAdapter extends BaseAppArrayAdapter {
	private static final String CNAME = AppListArrayAdapter.class.getSimpleName();
	
	public AppListArrayAdapter(Context context, List<Item> packages) {
		super(context, packages);
	}

    // -------------------------------------------------------------
    // Views
    // -------------------------------------------------------------

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		if(mViewArray != null && mViewArray.get(position) != null) {
			convertView = mViewArray.get(position);
			if(convertView != null) {
				return convertView;
			}
		}
		
		try {
            convertView = mPackages.get(position).getView(mInflater, convertView, this, position);
		} catch(Exception e) {
			if(BuildConfig.DEBUG)
				Logger.e(CNAME, ExceptionUtil.exception(e));
		} finally {
			if (mViewArray != null)
				mViewArray.append(position, convertView);
		}
        
        return convertView;
    }

    private List<Item> setListItem(List<PackageVO> packages) {
        List<Item> items = new ArrayList<Item>();
        for (PackageVO vo:packages) {
            if (vo.systemApp) {
                continue;
            }

            items.add(new AppListItem(getContext(), vo));
        }

        return items;
    }

}