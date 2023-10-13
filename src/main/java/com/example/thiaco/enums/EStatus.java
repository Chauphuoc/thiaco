package com.example.thiaco.enums;

import com.example.thiaco.exception.DataInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EStatus {
    CHINH_THUC("Chính thức"),
    THU_VIEC("Thử việc"),
    DAO_TAO("Đào tạo"),
    THOI_VU("Thời vụ");

    private String value;

    EStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, EStatus>
            NAME_MAP = Stream.of(values()).collect(Collectors.toMap(EStatus::toString, Function.identity()));

    public static EStatus fromString(final String name) {
        EStatus eStatus = NAME_MAP.get(name);
        if (eStatus == null) {
            throw new DataInputException(String.format("'%s' has no corresponding value. Accepted values: %s", name, Arrays.asList(values())));
        }
        return eStatus;
    }

    public static EStatus getEStatus(String value) {
        for (EStatus eStatus : EStatus.values()) {
            if (eStatus.getValue().equalsIgnoreCase(value)) {
                return eStatus;
            }
        }
        return null;
    }
}