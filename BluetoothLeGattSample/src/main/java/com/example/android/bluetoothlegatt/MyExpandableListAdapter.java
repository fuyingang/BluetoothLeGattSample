package com.example.android.bluetoothlegatt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView; 
import android.widget.BaseExpandableListAdapter;



public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ServiceNode> mGroupServices;

    public MyExpandableListAdapter(Context context, ArrayList<ServiceNode> groupServices) {

        this.context = context;
        this.mGroupServices = groupServices;
    } 

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        
        return mGroupServices.get(groupPosition).getCharacteristics().get(childPosition);
   } 

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
              View view, ViewGroup parent) {

        CharacteristicNode charaNode = (CharacteristicNode) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_row, null);
        }
  
        TextView charName = (TextView) view.findViewById(R.id.charName);
        charName.setText(charaNode.getName());
        
        TextView charUuid = (TextView) view.findViewById(R.id.charUUID);
        charUuid.setText(charaNode.getUuid());
        
        TextView charProp = (TextView) view.findViewById(R.id.charProp);
        charProp.setText("property: " + charaNode.getProp());

        TextView charPerm = (TextView) view.findViewById(R.id.charPerm);
        charPerm.setText("permission: " + charaNode.getPerm());

        return view;

    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return mGroupServices.get(groupPosition).getCharacteristics().size(); 
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupServices.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mGroupServices.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        ServiceNode serviceNode = (ServiceNode) getGroup(groupPosition);

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.group_view, null);
        }

        TextView serName = (TextView) view.findViewById(R.id.serviName);
        serName.setText(serviceNode.getName());

        TextView serUuid= (TextView) view.findViewById(R.id.serviUUID);
        serUuid.setText(serviceNode.getUuid());
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
