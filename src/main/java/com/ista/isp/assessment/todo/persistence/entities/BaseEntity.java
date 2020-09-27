package com.ista.isp.assessment.todo.persistence.entities;

import java.io.Serializable;

public abstract class BaseEntity<E> implements Serializable {

    public abstract E getId();
}
