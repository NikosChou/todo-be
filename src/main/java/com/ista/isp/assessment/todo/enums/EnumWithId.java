package com.ista.isp.assessment.todo.enums;

import com.ista.isp.assessment.todo.exceptions.EnumNotFoundException;

import java.util.EnumSet;

public interface EnumWithId<ID> {
    ID getId();

    static <I, E extends Enum<E> & EnumWithId<I>> E fromId(I id, Class<E> clazz) {
        //@formater:off
        return EnumSet.allOf(clazz).stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(() -> new EnumNotFoundException("Enum of " + clazz + " with id \"" + id + "\" not found"));
        //@formater:on
    }

}

