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
public class CustomerDao extends AbstractEntityDao {
    public CustomerDao() {
        super(Customer.class);
    }


    public List<Customer> tail(int maxResults) { //ищет заказы по кастомеру
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);
        criteria.select(root);

        criteria.orderBy(builder.desc(root.get(Customer_.nm)));

        TypedQuery<Customer> typed = getEntityManager().createQuery(criteria).setMaxResults(maxResults);
        return typed.getResultList();
    }

    public boolean exceedRequests(String ip, String template, int minutes, int units, int excid) {

        return false;
    }


    protected Predicate getSearchPredicate(CriteriaBuilder builder, Root root, String s) {
        return builder.or(
                builder.like(root.get(Customer_.id), s),
                builder.like(root.get(Customer_.nm), s));
    }
}