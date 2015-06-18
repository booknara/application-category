package com.github.booknara.appcategory.taks;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;


import com.github.booknara.appcategory.BuildConfig;
import com.github.booknara.appcategory.R;
import com.github.booknara.appcategory.adapter.AppListItem;
import com.github.booknara.appcategory.adapter.Item;
import com.github.booknara.appcategory.util.AnimationUtil;
import com.github.booknara.appcategory.util.ExceptionUtil;
import com.github.booknara.appcategory.util.Logger;
import com.github.booknara.appcategory.vo.PackageVO;

import java.io.File;


/**
 * 
 * @author : Daehee Han (@daniel_booknara)
 * @version : 1.0.0
 */
public class GetAppIconTask extends AsyncTask<String, Integer, Drawable> {
	private static final String TNAME = GetAppIconTask.class.getSimpleName();
	
	private Context c = null;
    private Item mBaseClass = null;

	private ImageView mAppIcon;
    private ImageView mSystemIcon;
	private PackageVO mpVO;
	private Drawable icon = null;

//    public int FADE_DURATION = 1500;
    public int FADE_DURATION;
	
	public GetAppIconTask(Context c, Item baseClass, ImageView appIcon, ImageView systemIcon, PackageVO pVO) {
		this.c = c;
        this.mBaseClass = baseClass;
		this.mAppIcon = appIcon;
        this.mSystemIcon = systemIcon;
		this.mpVO = pVO;
        this.FADE_DURATION = c.getResources().getInteger(android.R.integer.config_shortAnimTime);
	}	
	
	@Override
	protected Drawable doInBackground(String... params) {		
		try {
            if (mBaseClass instanceof AppListItem) {
                icon = c.getPackageManager().getApplicationIcon(mpVO.pname);
            }

			return icon;
		} catch(Exception e) {
			if(BuildConfig.DEBUG)
				Logger.e(TNAME, ExceptionUtil.exception(e));
		} catch(OutOfMemoryError e) {
            System.gc();
            if(BuildConfig.DEBUG)
                Logger.e(TNAME, ExceptionUtil.error(e));
        }
		
		return null;
	}

	@Override
	protected void onPostExecute(Drawable result) {	
		try {
            int color = c.getResources().getColor(android.R.color.white);
            Drawable defaultColor = new ColorDrawable(color);

			if (result == null) {
				Logger.e(TNAME, "Error for Getting App Icon, App Name : " + mpVO.appname);
				mAppIcon.setImageDrawable(defaultColor);
                mSystemIcon.setVisibility(View.INVISIBLE);
			} else {
                AnimationUtil.fadeInView(c, mAppIcon, defaultColor, result, FADE_DURATION);
                if (mpVO.systemApp) {
                    AnimationUtil.fadeInBackgroundView(c, mSystemIcon, R.drawable.system_ic_transition, FADE_DURATION);
                } else {
                    mSystemIcon.setVisibility(View.INVISIBLE);
                }

			}
		} catch(Exception e) {
			if(BuildConfig.DEBUG)
				Logger.e(TNAME, ExceptionUtil.exception(e));
		}
	}
	
}