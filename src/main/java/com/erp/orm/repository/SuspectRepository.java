package com.erp.orm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.Suspect;


public interface SuspectRepository extends CrudRepository<Suspect, Integer>, JpaSpecificationExecutor<Suspect>{

}
