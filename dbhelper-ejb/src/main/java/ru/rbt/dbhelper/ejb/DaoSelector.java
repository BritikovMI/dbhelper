package ru.rbt.dbhelper.ejb;


import javax.inject.Inject;

public class DaoSelector {

    @Inject
    private OrderDao orderDao;

    public OrderDao daoSelect(Long number) {
        return orderDao;
    }
}
