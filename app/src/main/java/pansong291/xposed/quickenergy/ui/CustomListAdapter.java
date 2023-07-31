package pansong291.xposed.quickenergy.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import pansong291.xposed.quickenergy.R;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mDataList;
    private int[] mIconList;

    public CustomListAdapter(Context context, ArrayList<String> dataList, int[] iconList) {
        super(context, R.layout.list_item_setting, dataList);
        mContext = context;
        mDataList = dataList;
        mIconList = iconList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取当前列表项的数据
        String itemName = mDataList.get(position);
        int iconResId = mIconList[position];

        // 创建列表项的布局
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_setting, parent, false);

        // 获取列表项中的ImageView和TextView
        ImageView iconView = itemView.findViewById(R.id.icon);
        TextView textView = itemView.findViewById(R.id.text);

        // 设置ImageView和TextView的内容
        iconView.setImageResource(iconResId);
        textView.setText(itemName);

        // 返回列表项的布局
        return itemView;
    }
}


