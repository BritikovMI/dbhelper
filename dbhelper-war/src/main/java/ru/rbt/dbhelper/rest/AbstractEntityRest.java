package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.jpa.AbstractEntity;
import ru.rbt.dbhelper.ejb.AbstractEntityDao;
import ru.rbt.dbhelper.jpa.Order;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Базовая абстракция микросервисов иплементирует основные CRUD операции
 * сущностей наследуемых от AbstractDbhelperEntity,
 * методы поиска по имени, псевдониму, идентификатору,
 * методы получения коллекций.
 *
 * Created by misha on 03.03.17.
 */
public abstract class AbstractEntityRest<I extends Serializable,
        E extends AbstractEntity<I>, R extends AbstractEntityDao<I, E>> {

    @Inject
    private R repo;

    private Class<E> clazz;

    public AbstractEntityRest(Class<E> clazz) {
        this.clazz = clazz;
    }

    protected R getRepo() {
        return repo;
    }

    protected E getEntity(I id) {
        return repo.find(id);
    }

    public String echo() {
        return getClass().getSimpleName() + " echo test";
    }

    public void create(E entity) {
        repo.save(entity);
    }

    public E read(I id) {
        return getEntity(id);
    }

    public void update(E entity) {
        repo.update(entity);
    }

    public void delete(I id) {
        repo.remove(getEntity(id));
    }

    public List<E> list() {
        return repo.list();
    }

}
