package ru.rbt.dbhelper.ejb;

import ru.rbt.dbhelper.jpa.*;

import javax.ejb.Stateless;
import javax.persistence.criteria.*;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Stateless
public class CustomerDao extends AbstractEntityDao<Long, Customer> {
    public CustomerDao() {
        super(Customer.class);
    }
}