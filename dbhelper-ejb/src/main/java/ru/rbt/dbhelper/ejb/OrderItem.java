package ru.rbt.dbhelper.ejb;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Entity
@Table(name = "BRM_ORDER_ITEM")
public class OrderItem implements Serializable{//implements serializable -abstractdbhelper - dbhelperentity//embdeded
    private static final long serialVersionUID = -6576973010070221989L;
    /**
     * Идентификатор
     */
    private Long id;
    private Customer orderId;
    private Product productId;


    @Id
    @Column(name = "ORDER_ITEM_ID" )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID_PK" , nullable = false)
    public Customer getOrderId() {
        return orderId;
    }

    public void setOrderId(Customer orderId) {
        this.orderId = orderId;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID_PK" , nullable = false)
    public Product getProductId() {
        return productId;
    } //list orders //one to many// user employee

    public void setProductId(Product productId) {
        this.productId = productId;
    }



}