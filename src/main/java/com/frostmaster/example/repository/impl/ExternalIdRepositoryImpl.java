package com.frostmaster.example.repository.impl;

import com.frostmaster.example.repository.ExternalIdRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

public class ExternalIdRepositoryImpl<T, ID extends Serializable>
    extends SimpleJpaRepository<T, ID> implements ExternalIdRepository<T, ID> {

  private EntityManager entityManager;

  public ExternalIdRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  @Transactional
  public <S extends T> List<S> saveAllWithProvidedId(Iterable<S> entities) {
    Assert.notNull(entities, "The given Iterable of entities not be null!");
    List<S> result = new ArrayList();
    Iterator entitiesIterator = entities.iterator();

    while(entitiesIterator.hasNext()) {
      S entity = (S) entitiesIterator.next();
      result.add(this.saveWithProvidedId(entity));
    }

    return result;
  }

  @Transactional
  public <S extends T> S saveWithProvidedId(S entity) {
    this.entityManager.persist(entity);
    return entity;
  }
}
