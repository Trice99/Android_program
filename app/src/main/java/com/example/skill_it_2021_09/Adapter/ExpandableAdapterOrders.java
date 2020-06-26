package com.example.skill_it_2021_09.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.skill_it_2021_09.Data.StringPair;
import com.example.skill_it_2021_09.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExpandableAdapterOrders extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<StringPair>> _listDataChild;

    public ExpandableAdapterOrders(Context context, List<String> listDataHeader, HashMap<String, List<StringPair>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return Objects.requireNonNull(this._listDataChild.get(this._listDataHeader.get(groupPosition))).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final StringPair childText = (StringPair) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.ordersitems, null);
        }
        if(childPosition%2 == 0){
            convertView.setBackgroundColor(Color.LTGRAY);
        }else{
            convertView.setBackgroundColor(Color.WHITE);
        }
        TextView childname = (TextView) convertView.findViewById(R.id.productname);
        TextView childcount = (TextView) convertView.findViewById(R.id.count);

        childname.setText(childText.getName());
        childcount.setText(childText.getCount());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Objects.requireNonNull(this._listDataChild.get(this._listDataHeader.get(groupPosition))).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.ordersrow, null);
            }
            if(groupPosition%2 == 0){
                convertView.setBackgroundColor(Color.rgb(179, 229, 252));
            }else{
                convertView.setBackgroundColor(Color.rgb(165, 214, 167));
            }
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.header);
            lblListHeader.setText(headerTitle);
            return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
