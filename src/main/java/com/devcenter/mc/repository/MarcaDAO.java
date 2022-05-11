package com.devcenter.mc.repository;

import com.devcenter.mc.model.Marca;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaDAO extends CrudRepository<Marca, Long> {

}
