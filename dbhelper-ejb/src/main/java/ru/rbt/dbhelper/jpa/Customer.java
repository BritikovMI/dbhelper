package ru.rbt.dbhelper.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    private Long id;
    private String nm;

    public Customer() {
    }

    @Id
    @Column(name = "ID_PK" )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "NM", nullable = false)
    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

}