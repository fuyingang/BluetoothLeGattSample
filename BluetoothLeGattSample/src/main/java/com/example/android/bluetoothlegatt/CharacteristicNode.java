package com.example.android.bluetoothlegatt;

import java.util.ArrayList;
import android.bluetooth.BluetoothGattCharacteristic;

    public class CharacteristicNode {

        private String name;
        private String uuid;
        private int properties;
        private int permissions;
        private ArrayList<DescriptorNode> mDescriptors = new ArrayList<DescriptorNode>();
        private BluetoothGattCharacteristic characteristic;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public void setProp(int prop) {
            this.properties = prop;
        }
        public int getProp() {
            return properties;
        }
        public void setPerm(int perm) {
            this.permissions = perm;
        }
        public int getPerm() {
            return permissions;
        }
        public void setChara(BluetoothGattCharacteristic chara) {
            this.characteristic = chara;
        }

        public BluetoothGattCharacteristic getChara() {
            return characteristic; 
        }
    }

