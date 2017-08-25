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
public class OrderItemDao extends AbstractEntityDao {

    public OrderItemDao() {
        super(Customer.class);
    }

    @Deprecated
    public List<OrderItem> getProductsByCustomerId(Long customerId){//А вот тут не продукты получаем а список заказов,
                                                                    // значит метод надо правильно называть
                                                                    // getOrderItemsByCustomerId ---- Получаем products определенного Кастомера
//        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<OrderItem> criteria = builder.createQuery(OrderItem.class);
//        Root<OrderItem> root = criteria.from(OrderItem.class);
//        criteria.select(root);
////        criteria.where(builder.equal(root.get(OrderItem_.order).get(Customer_.id), customerId)).where(builder.equal(root.get(OrderItem_.product), root.get(OrderItem_.order))).where(builder.equal(root.get(OrderItem_.product).get(Product_.id), root.get(OrderItem_.product)));
//        criteria.where(builder.equal(root.get(OrderItem_.order).get(Customer_.id), customerId));
//        criteria.where(builder.equal(root.get(OrderItem_.product), root.get(OrderItem_.order)));
//        criteria.where(builder.equal(root.get(OrderItem_.product).get(Product_.id), root.get(OrderItem_.product)));
//        TypedQuery<OrderItem> query = getEntityManager().createQuery(criteria);
//        List<OrderItem> result = query.getResultList();
//        return result;
        return null;
    }

    public List<Product> getProductsByCustomerIdentifier(Long customerId){//Вот тут получаем products определенного Кастомера
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OrderItem> criteria = builder.createQuery(OrderItem.class);
        Root<OrderItem> root = criteria.from(OrderItem.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(OrderItem_.order).get(Order_.customer).get(Customer_.id), customerId));
        TypedQuery<OrderItem> query = getEntityManager().createQuery(criteria);
        List<OrderItem> list = query.getResultList();
        // добавляем в такой вот сет, с компаратором, чтобы отсортировать по наименованию
        // (product type! я же говорил поменяй на getName() )
        // и удаляем дубликаты, потому как в разных заказах могут повторяться одни и те же продукты
        // использовать join на таблицу продуктов можно было бы если бы ты прописал обратный маппинг в сущности продакт
        // на сущность ордерайтем. но ее нет поэтому так делаем. Тем более что трисет очень быстр.
        // Но если будешь работать с табличками миллиониками, возможно лучше будет все же замапить и сделать джойном.
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
