package ru.rbt.dbhelper.jpa;

import java.io.Serializable;

/**
 * Created by KryukovMV on 16.03.2017.
 */
public interface DbhelperUiEntity<I extends Serializable> {

    I getId();

    void setId(I id);

    String getName();

    void setName(String name);
}
