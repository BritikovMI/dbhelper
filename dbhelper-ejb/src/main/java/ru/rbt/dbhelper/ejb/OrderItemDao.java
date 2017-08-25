package ru.rbt.dbhelper.ejb;

import ru.rbt.dbhelper.jpa.*;

import javax.ejb.Stateless;
import javax.persistence.criteria.*;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Stateless
public class OrderItemDao extends AbstractEntityDao<Long, OrderItem>{

    public OrderItemDao() {
        super(OrderItem.class);
    }

    public List<Product> getProductsByCustomerIdentifier(Long customerId){//Вот тут получаем products определенного Кастомера
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OrderItem> criteria = builder.createQuery(OrderItem.class);
        Root<OrderItem> root = criteria.from(OrderItem.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(OrderItem_.order).get(Order_.customer).get(Customer_.id), customerId));
        TypedQuery<OrderItem> query = getEntityManager().createQuery(criteria);
        List<OrderItem> list = query.getResultList();
        TreeSet<Product> products = new TreeSet<>((o1, o2) -> {
            if (o1 == null && o2 == null) return 0;
            else if (o1 == null) return -1;
            else if (o2 == null) return 1;
            return o1.getProductType().compareTo(o2.getProductType());
        });
        list.forEach(item -> products.add(item.getProduct()));
        return new ArrayList<>(products);
    }
}
