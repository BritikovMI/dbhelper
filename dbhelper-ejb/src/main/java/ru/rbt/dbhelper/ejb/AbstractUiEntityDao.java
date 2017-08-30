package ru.rbt.dbhelper.ejb;

import ru.rbt.dbhelper.jpa.AbstractUiEntity;
import ru.rbt.dbhelper.jpa.AbstractUiEntity_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static ru.rbt.dbhelper.ejb.PuProvider.DBHELPER_PU;

/**
 * Базовая абстракция для фабрик иплементирует некоторые основные CRUD операции
 *
 * Created by misha on 03.03.17.
 */
public abstract class AbstractUiEntityDao<ID extends Serializable, E extends AbstractUiEntity<ID>> {

    @PersistenceContext(unitName = DBHELPER_PU)
    private EntityManager em;

    private Class<E> clazz;

    public AbstractUiEntityDao(Class<E> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public E find(ID id) {
        return getEntityManager().find(clazz, id);
    }

    public void update(E entity) {
        getEntityManager().merge(entity);
    }

    public void save(E entity) {
        getEntityManager().persist(entity);
    }

    public void delete(E entity) {
        getEntityManager().remove(entity);
    }

    public E findByName(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(clazz);
        Root<E> root = criteria.from(clazz);
        criteria.select(root).where(builder.like(root.get(AbstractUiEntity_.name), name));
        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    protected abstract Predicate getSearchPredicate(CriteriaBuilder builder, Root<E> root, String s);
}