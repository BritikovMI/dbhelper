package ru.rbt.dbhelper.ejb;


import ru.rbt.dbhelper.jpa.*;
import ru.rbt.dbhelper.jpa.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Stateless
public class OrderDao extends AbstractEntityDao {

    public OrderDao() {
        super(Order.class);
    }

    public List<Order> getOrdersByCustomerId(Long customerId){
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Order_.customer).get(Customer_.id), customerId));
        TypedQuery<Order> query = getEntityManager().createQuery(criteria);
        List<Order> result = query.getResultList();
        return result;
//        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
//        Root<Customer> root = query.from(Customer.class);
//        Join<Order,Customer> joinTab = root.join(String.valueOf(Order_.customer));
//        query.select(joinTab).where(builder.equal(root.get(Customer_.id), id));
//        List<Customer> custOrders = getEntityManager().createQuery(query).getResultList();
//        return custOrders;
    }
}
