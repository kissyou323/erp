package com.erp.orm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.EndCase;

public interface EndCaseRepository extends CrudRepository<EndCase, Integer>, JpaSpecificationExecutor<EndCase>{

}
