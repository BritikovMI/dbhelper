package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.ejb.AbstractUiEntityDao;
import ru.rbt.dbhelper.jpa.AbstractUiEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Базовая абстракция микросервисов иплементирует основные CRUD операции
 * сущностей наследуемых от AbstractUiEntity,
 * методы поиска по имени, псевдониму, идентификатору,
 * методы получения коллекций.
 *
 * Created by misha on 03.03.17.
 */
public abstract class AbstractUiEntityRest<I extends Serializable, 
        E extends AbstractUiEntity<I>, R extends AbstractUiEntityDao<I, E>> {

    @Inject
    private R repo;

    private Class<E> clazz;

    public AbstractUiEntityRest(Class<E> clazz) {
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
        repo.delete(getEntity(id));
    }

    public E findByName(String name) {
        return repo.findByName(name);
    }

}
