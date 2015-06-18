package com.github.booknara.appcategory.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;

import com.github.booknara.appcategory.R;


/**
 * @author : Daehee Han(@daniel_booknara)
 * @version : 1.0.0
 */
public class HeaderItem implements Item{
    private Context context;
    private final String title;

    public HeaderItem(Context context, String title) {
        this.context = context;
        this.title = title;
    }

    @Override
    public int getViewType() {
        return RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, BaseAppArrayAdapter adapter, int position) {
        convertView = inflater.inflate(R.layout.layout_app_header, null);

        AppCompatTextView text = (AppCompatTextView) convertView.findViewById(R.id.app_section_name_textview);
        text.setText(title);

        return convertView;
    }

    public Context getContext() {
        return context;
    }
}
