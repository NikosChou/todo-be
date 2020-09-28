package com.ista.isp.assessment.todo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ItemStatusEnum implements EnumWithId<Integer> {
    //@formatter:off
    CREATED                 (10,  "created"),
    COMPLETED               (20,  "completed");
    //@formatter:on

    ItemStatusEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }


    private final int id;
    private final String value;

    @JsonValue
    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ItemStatusEnum fromId(Integer id) {
        return EnumWithId.fromId(id, ItemStatusEnum.class);
    }
}
