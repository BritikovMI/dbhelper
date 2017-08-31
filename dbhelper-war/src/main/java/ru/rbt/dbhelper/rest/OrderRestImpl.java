package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.jpa.Order;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Stateless
public class OrderRestImpl extends Order implements OrderRest {

    public OrderRestImpl (){
        super(Order.class);
    }

    @Override
    public List<Order> findByName(Long id, String name) {
        return null;
    }
}