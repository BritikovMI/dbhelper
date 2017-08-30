package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.jpa.Order;
import ru.rbt.dbhelper.ejb.OrderDao;

import javax.ejb.Stateless;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Stateless
public class OrderRestImpl extends AbstractUiEntityRest<Long, Order, OrderDao> implements OrderRest {

    public OrderRestImpl() {
        super(Order.class);
    }

    @Override
    public Order findByAliasOrName(Long id, String name) {
        return null;
    }
}
