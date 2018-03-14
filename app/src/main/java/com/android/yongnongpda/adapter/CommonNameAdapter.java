package com.android.yongnongpda.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.yongnongpda.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发货机构，发货仓库，产品名称显示
 * Created by shijie.yang on 2017/7/27.
 */

public abstract class CommonNameAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> list;
    private LayoutInflater inflater = null;

    public CommonNameAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_data_item, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        showText(holder, list.get(position), holder.tvData);
        return convertView;
    }

    public abstract void showText(ViewHolder holder, T t, TextView textView);

    public class ViewHolder {
        @BindView(R.id.tvData)
        TextView tvData;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}