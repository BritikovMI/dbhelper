package ru.rbt.dbhelper.ejb;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Entity
@Table(name = "BRM_CUSTOMER")
public class Customer implements Serializable{//implements serializable -abstractdbhelper - dbhelperentity//embdeded
    private static final long serialVersionUID = -6576973010070221989L;
    /**
     * Идентификатор
     */
    private List<Long> id;
    private String nm;

    public Customer() {
    }

    @Id
    @Column(name = "ID_PK" )
    public List<Long> getId() {
        return id;
    }

    public void setId(List<Long> id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "DATE_OF", nullable = false)
    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

}