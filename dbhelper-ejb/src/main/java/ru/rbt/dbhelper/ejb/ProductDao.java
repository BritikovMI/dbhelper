package ru.rbt.dbhelper.ejb;

import ru.rbt.dbhelper.jpa.Product;

import javax.ejb.Stateless;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Stateless
public class ProductDao extends AbstractEntityDao<Long, Product> {

    public ProductDao() {
        super(Product.class);
    }


}