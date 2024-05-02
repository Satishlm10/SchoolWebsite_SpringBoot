package com.eazybytes.eazyschool.model;

import lombok.Data;

@Data
public class HolidayDto {
    private final String day;
    private final String reason;
    private final Type type;

    public enum Type{
        FESTIVAL,
        FEDERAL
    }

}
