package com.zebra.rfid.Util;

public class ConstantsInternal {


    public enum AppMode {
        Dev("Dev", 1),
        Staging("Staging", 2),
        Prod("Prod", 3);

        private String stringValue;
        private int intValue;

        private AppMode(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        public static AppMode fromInteger(int x) {
            switch (x) {
                case 1:
                    return Dev;
                case 2:
                    return Staging;
                case 3:
                    return Prod;
            }
            return null;
        }

        public int getValue() {
            return intValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }
}
