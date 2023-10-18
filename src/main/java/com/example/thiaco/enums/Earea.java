package com.example.thiaco.enums;

public enum Earea {
    NHA_MAY1("Nhà máy may 1"),
    NHA_MAY2("Nhà máy may 2"),
    BAO_BI("Nhà máy bao bì");

    private String value;

    Earea (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Earea getArea(String value) {
        for (Earea earea : Earea.values()) {
            if (earea.getValue().equalsIgnoreCase(value)) {
                return earea;
            }
        }
        return null;
    }
}
