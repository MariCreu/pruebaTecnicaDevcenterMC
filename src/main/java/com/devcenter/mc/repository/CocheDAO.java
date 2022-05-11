package com.devcenter.mc.repository;

import com.devcenter.mc.model.Coche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocheDAO extends CrudRepository<Coche, Long> {

    List<Coche> findAll();

}
