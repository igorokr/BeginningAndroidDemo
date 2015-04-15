package com.ba.presentation.beginning_android.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by iroman on 3/31/2015.
 */
public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mInflater;
    private MainActivity.DrawerMenuItem[] mMenuItems;

    public MenuAdapter(Context context, MainActivity.DrawerMenuItem[] menuItems) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuItems = menuItems;
        if (menuItems == null) {
            throw new RuntimeException();
        }
    }

    @Override
    public int getCount() {
        return mMenuItems.length;
    }

    @Override
    public Object getItem(int position) {
        return mMenuItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Holder {
        public TextView tvText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Holder holder;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.navigation_drawer_item, parent, false);
            holder = new Holder();
            holder.tvText = (TextView) view.findViewById(R.id.text1);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.tvText.setText(mMenuItems[position].mName);
        return view;
    }
}
