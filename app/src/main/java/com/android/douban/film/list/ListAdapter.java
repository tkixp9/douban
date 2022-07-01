package com.android.douban.film.list;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.douban.film.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class ListAdapter extends BaseAdapter {

    private static String TAG = "ListAdapter";
    private ArrayList mData;
    private Context mContext;

    public ListAdapter(Context context, ArrayList data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return getItemViewType(i) == 1 ? -1 : mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        boolean loadingType = getItemViewType(i) == 1;
        if (loadingType) {
            if (convertView == null) {
                TextView tv = new TextView(mContext);
                tv.setText("正在加载...");
                tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setTranslationX(140);
                tv.setGravity(Gravity.RIGHT);
                tv.setPadding(0, 20, 0, 80);
                convertView = tv;
            }
        } else {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.film_item, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Glide.with(mContext).load(((HashMap) mData.get(i)).get("img").toString()).into(holder.img);
            holder.name.setText(((HashMap) mData.get(i)).get("name").toString());
        }
        return convertView;
    }

    public int getItemViewType(int position) {
        return mData.size() == position ? 1 : 0;
    }

    public int getViewTypeCount() {
        return 2;
    }

    private final class ViewHolder {
        ImageView img;
        TextView name;
    }
}