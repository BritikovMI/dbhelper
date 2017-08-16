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
public class OrderDao extends AbstractEntityDao {
    public OrderDao() {
        super(Order.class);
    }


    public List<Order> tail(int maxResults) { //ищет заказы по кастомеру
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root);
        criteria.orderBy(builder.desc(root.get(String.valueOf(Order_.id))));
        TypedQuery<Order> typed = getEntityManager().createQuery(criteria).setMaxResults(maxResults);
        return typed.getResultList();
    }

    public boolean exceedRequests(String ip, String template, int minutes, int units, int excid) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.roll(units, (-1 * minutes));
//        Date recent = c.getTime();
//        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
//        Root<Journal> root = criteria.from(Journal.class);
//        criteria.select(builder.count(root));
//        Expression<String> expression = builder.function(PostgresqlExtensionsDialect.FUNC_JSONB_MAP_BY_KEY,
//                String.class, root.get("parameters"), builder.literal("remote-addr"));
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(builder.like(expression, builder.literal(ip)));
//        predicates.add(builder.like(root.get(Journal_.name), template));
//        predicates.add(builder.greaterThan(root.get(Journal_.registered), recent));
//        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
//        TypedQuery<Long> typed = getEntityManager().createQuery(criteria);
//        Long count = typed.getSingleResult();
//        if (count != null && count > excid) return true;
////        String q = "SELECT count(*) FROM audit.journal " +
////                "where parameters ->> 'remote-addr' like '127.0.0.1' " +
////                "and name like 'authentication-fail%' " +
////                "and registered > '2017-03-06 13:34';";
        return false;
    }


    protected Predicate getSearchPredicate(CriteriaBuilder builder, Root root, String s) {
        return builder.or(
                builder.like(root.get(Order_.id), s),
                builder.like(root.get(Order_.date), s),
                builder.like(root.get(Order_.customer), s));
    }
}
