package com.craftelix.repository;

import com.craftelix.entity.BaseEntity;
import com.craftelix.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K ,E> {

    private final Class<E> entityClass;

    @Override
    public E save(E entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(id);
        session.flush();
    }

    @Override
    public void update(E entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return Optional.ofNullable(session.find(entityClass, id));
    }

    @Override
    public List<E> findAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);
        return session.createQuery(criteria).list();
    }
}
