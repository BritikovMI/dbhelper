package ru.rbt.dbhelper.other;

import ru.rbt.dbhelper.ejb.CustomerDao;
import ru.rbt.dbhelper.ejb.OrderDao;
import ru.rbt.dbhelper.ejb.OrderItemDao;
import ru.rbt.dbhelper.ejb.ProductDao;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Это CDI bean тут еще нет транзакций
 * тут мы еще не лезе в базульку
 * тут оперируем DAO-layer
 *
 * Created by er23887 on 22.08.2017.
 */
public class DaoManager {

    @Inject
    private CustomerDao customerDao;
    @Inject
    private OrderDao orderDao;
    @Inject
    private ProductDao productDao;
    @Inject
    private OrderItemDao orderItemDao;

    public List handleRequest(String name) {
        if (name == "customer-order") {
            return orderDao.getOrdersByCustomerId(1L);
        }
        return Collections.emptyList();
    }
}
