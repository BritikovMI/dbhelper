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

}
