package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.Record;

public interface RecordRepository extends CrudRepository<Record, Integer>,JpaSpecificationExecutor<Record> {

	@Query(value = "select r.* from t_record r where r.record_no = ?1", nativeQuery = true)
	List<Record> findByAssetsNo(String recordNo);

	@Query(value = "SELECT record_id, record_no, record_style, record_name, record_time, use_department, agent, use_explain, status, style FROM t_record where  (record_no in (SELECT assets_no FROM t_fixedassets where status = 3) or  record_no in (SELECT equipment_no FROM t_equipment  where status = 3) or record_no in (SELECT possessions_no FROM t_possessions where status = 3) or record_no in (SELECT case_no FROM t_case where file_status = 3))",nativeQuery = true)
	List<Record> findRecordList();
	
}
