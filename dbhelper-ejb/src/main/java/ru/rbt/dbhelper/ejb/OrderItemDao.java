package ru.rbt.dbhelper.ejb;

import ru.rbt.dbhelper.jpa.*;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Stateless
public class OrderItemDao extends AbstractEntityDao {

    public OrderItemDao() {
        super(Customer.class);
    }

    public List<OrderItem> getProductsByCustomerId(Long customerId){
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OrderItem> criteria = builder.createQuery(OrderItem.class);
        Root<OrderItem> root = criteria.from(OrderItem.class);
        criteria.select(root);
//        criteria.where(builder.equal(root.get(OrderItem_.order).get(Customer_.id), customerId)).where(builder.equal(root.get(OrderItem_.order), root.get(OrderItem_.product))).where(builder.equal(root.get(OrderItem_.product), root.get(OrderItem_.product).get(Product_.id)));
        criteria.where(builder.equal(root.get(OrderItem_.order).get(Customer_.id), customerId));
        criteria.where(builder.equal(root.get(OrderItem_.product), root.get(OrderItem_.order)));
        criteria.where(builder.equal(root.get(OrderItem_.product).get(Product_.id), root.get(OrderItem_.product)));
        TypedQuery<OrderItem> query = getEntityManager().createQuery(criteria);
        List<OrderItem> result = query.getResultList();
        return result;
    }
}
