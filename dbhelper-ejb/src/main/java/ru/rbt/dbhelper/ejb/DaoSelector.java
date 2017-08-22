package ru.rbt.dbhelper.ejb;



public class DaoSelector {
    public static OrderDao daoSelect(Long number) {
        OrderDao oDao = new OrderDao();
        oDao.getCustomerOrders(number);
        return oDao;
    }
}
