package com.frostmaster.example.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExternalIdRepository<T, ID extends Serializable>
    extends JpaRepository<T, ID> {

  <S extends T> List<S> saveAllWithProvidedId(Iterable<S> entities);

  <S extends T> S saveWithProvidedId(S entity);

}
