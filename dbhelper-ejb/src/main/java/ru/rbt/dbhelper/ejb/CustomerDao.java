package ru.rbt.dbhelper.ejb;

import ru.rbt.dbhelper.jpa.Customer;

import javax.ejb.Stateless;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Stateless
public class CustomerDao extends AbstractEntityDao<Long, Customer> {
    public CustomerDao() {
        super(Customer.class);
    }
}