package com.example.android.bluetoothlegatt;

import java.util.ArrayList;

    public class ServiceNode {

        private String name;
        private String uuid;
        private ArrayList<CharacteristicNode> mCharacteristics = new ArrayList<CharacteristicNode>();

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

        public ArrayList<CharacteristicNode> getCharacteristics() {
            return mCharacteristics;
        }

        public void setCharacteristics(ArrayList<CharacteristicNode> extCharacteristics) {
            this.mCharacteristics = extCharacteristics;
        }
    }

