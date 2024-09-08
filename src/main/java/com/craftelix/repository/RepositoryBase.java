package com.craftelix.repository;

import com.craftelix.entity.BaseEntity;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K ,E> {

    private final Class<E> entityClass;
    protected final Session session;

    @Override
    public E save(E entity) {
        session.save(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        session.delete(id);
        session.flush();
    }

    @Override
    public void update(E entity) {
        session.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(session.find(entityClass, id));
    }

    @Override
    public List<E> findAll() {
        CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);
        return session.createQuery(criteria).list();
    }
}
