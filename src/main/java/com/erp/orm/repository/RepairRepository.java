package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.Repair;

public interface RepairRepository extends CrudRepository<Repair, Integer>,JpaSpecificationExecutor<Repair> {

	@Query(value = "select r.* from t_repair r where r.repair_id = ?1", nativeQuery = true)
	List<Repair> findByrepairNo(String repairNo);
	
	@Query(value = "select r.* from t_repair r where r.assets_equipment_id = ?1", nativeQuery = true)
	List<Repair> findByRepairAssets_id (Integer Assets_equipment_id);
}
