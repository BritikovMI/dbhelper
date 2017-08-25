package ru.rbt.dbhelper.other;

import ru.rbt.dbhelper.ejb.CustomerDao;
import ru.rbt.dbhelper.ejb.OrderDao;
import ru.rbt.dbhelper.ejb.OrderItemDao;
import ru.rbt.dbhelper.ejb.ProductDao;
import ru.rbt.dbhelper.jpa.Order;
import ru.rbt.dbhelper.jpa.OrderItem;
import ru.rbt.dbhelper.jpa.Product;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Это CDI bean тут еще нет транзакций
 * тут мы еще не лезе в базульку
 * тут оперируем DAO-layer
 * <p>
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

    public List<String> handleRequest(String name) {
        List<String> result = new ArrayList<>();
        if (name.equals("customer-order")) {
            List<Order> orders = orderDao.getOrdersByCustomerId(1L);
            orders.forEach(order -> result.add(order.toString()));
        } else if (name.equals("product-customer")) {
            List<Product> products = orderItemDao.getProductsByCustomerIdentifier(2L);
            products.forEach(product -> result.add(product.getProductType()));
        }
        return result;
    }
}
