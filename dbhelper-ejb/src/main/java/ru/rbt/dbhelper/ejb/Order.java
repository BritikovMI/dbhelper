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
@Table(name = "BRM_ORDER")
public class Order implements Serializable{//implements serializable -abstractdbhelper - dbhelperentity//embdeded
    private static final long serialVersionUID = -6576973010070221989L;
    /**
     * Идентификатор
     */
    private Long id;
    private String date;
    private Long customer;


    @Id
    @Column(name = "ID_PK" )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "DATE_OF", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    Customer cust = new Customer();
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID_PK" , nullable = false)
    public List getCustomer() {
        return cust.getId();
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }





}
