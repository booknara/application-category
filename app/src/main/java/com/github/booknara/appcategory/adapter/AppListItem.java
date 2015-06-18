package com.github.booknara.appcategory.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.booknara.appcategory.R;
import com.github.booknara.appcategory.taks.GetAppIconTask;
import com.github.booknara.appcategory.vo.PackageVO;


/**
 * Created by Daehee Han on 4/22/14.
 */
public class AppListItem implements Item {
    private static final String CNAME = AppListItem.class.getSimpleName();
    private Context context;
    public PackageVO vo;

    public AppListItem(Context context, PackageVO vo) {
        this.context = context;
        this.vo = vo;
    }

    @Override
    public int getViewType() {
        return RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, BaseAppArrayAdapter adapter, int position) {
        AppListItemViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_app_list_item, null);

            holder = new AppListItemViewHolder();

            holder.appIcon = (ImageView) convertView.findViewById(R.id.image_app_thumbnail);
            holder.systemIcon = (ImageView) convertView.findViewById(R.id.app_system);
            holder.description = (ViewGroup) convertView.findViewById(R.id.description_layout);
            holder.appName = (AppCompatTextView) convertView.findViewById(R.id.text_app_name);
            holder.appPackageName = (AppCompatTextView) convertView.findViewById(R.id.text_app_package_name);
            holder.appSize = (AppCompatTextView) convertView.findViewById(R.id.text_app_size);

        } else {

        }

        //Retrieve package from items
        final PackageVO item = vo;

        if (item.pname == null || item.pname.isEmpty()) {
            holder.appName.setText(R.string.lbl_no_package_name);
        } else {
            holder.appPackageName.setText(item.pname);
        }

        if (item.versionName == null) {
            holder.appName.setText(item.appname);
        } else {
            holder.appName.setText(item.appname + " " + item.versionName);
        }

        holder.appSize.setVisibility(View.GONE);

        new GetAppIconTask(context, this, holder.appIcon, holder.systemIcon, item).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");

        return convertView;
    }

    public static class AppListItemViewHolder {
        public ImageView appIcon;
        public ImageView systemIcon;
        public ViewGroup description;
        public AppCompatTextView appName;
        public AppCompatTextView appPackageName;
        public AppCompatTextView appSize;
    }
}
