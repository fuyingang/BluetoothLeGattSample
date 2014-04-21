package com.example.android.bluetoothlegatt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView; 
import android.widget.BaseExpandableListAdapter;

import android.bluetooth.BluetoothGattDescriptor;



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

        int count;
        CharacteristicNode charaNode = (CharacteristicNode) getChild(groupPosition, childPosition);

        List<BluetoothGattDescriptor> groupDescriptors = new ArrayList<BluetoothGattDescriptor>();

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

        groupDescriptors = charaNode.getDescriptors();
        count = groupDescriptors.size();

        if (count > 0) {

            String uuid = null;

            TextView desDescriptor = (TextView) view.findViewById(R.id.desDescriptor);
            desDescriptor.setVisibility(0);

            BluetoothGattDescriptor currentDescriptor;

            currentDescriptor = groupDescriptors.get(0);

            uuid = currentDescriptor.getUuid().toString();
            TextView desUser = (TextView) view.findViewById(R.id.desUser);
            desUser.setVisibility(0);
            desUser.setText(SampleGattAttributes.lookup(uuid, "unknown"));

            TextView desUuid = (TextView) view.findViewById(R.id.desUuid);
            desUuid.setVisibility(0);
            desUuid.setText(uuid);

            TextView desPerm= (TextView) view.findViewById(R.id.desPerm);
            desPerm.setVisibility(0);
            desPerm.setText(""+ currentDescriptor.getPermissions());

            if (count > 1) {
                currentDescriptor = groupDescriptors.get(1);

                uuid = currentDescriptor.getUuid().toString();
                TextView desUser2 = (TextView) view.findViewById(R.id.desUser2);
                desUser2.setVisibility(0);
                desUser2.setText(SampleGattAttributes.lookup(uuid, "unknown"));

                TextView desUuid2 = (TextView) view.findViewById(R.id.desUuid2);
                desUuid2.setVisibility(0);
                desUuid2.setText(currentDescriptor.getUuid().toString());
    
                TextView desPerm2= (TextView) view.findViewById(R.id.desPerm2);
                desPerm2.setVisibility(0);
                desPerm2.setText(""+ currentDescriptor.getPermissions());

            }

       
        }

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
