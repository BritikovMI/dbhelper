package ru.rbt.dbhelper.ejb;

import java.io.Serializable;

/**
 * Created by KryukovMV on 16.03.2017.
 */
public interface DbhelperEntity<ID extends Serializable> {

    ID getId();

    void setId(ID id);
}
