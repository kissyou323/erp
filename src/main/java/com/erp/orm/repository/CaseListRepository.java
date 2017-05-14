package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.CaseList;

public interface CaseListRepository extends CrudRepository<CaseList, Integer>,
		JpaSpecificationExecutor<CaseList> {

	@Query(value = "select * from t_caselist where police_name = ?1", nativeQuery = true)
	List<CaseList> findCaseListByUserLogin(String policeName);

	/**
	 * 查询出结案日期是要求是今天的督案单
	 * @return
	 */
	@Query(value = "select * from t_caselist where TO_DAYS(caseend_time) >= TO_DAYS(NOW()) and state = 2", nativeQuery = true)
	List<CaseList> findCaseList();
	
	
	
	@Query(value = "select * from t_caselist where caselist_no = ?1", nativeQuery = true)
    CaseList findByCaseListNo(String caseListNo);
	
	/**
	 * case_id = ?1 and caselist_status = 1 and state != 5
	 * 查询案件编号为入参的督案单,并且要求是督案单不是督案单消息,并且不能等于5(5为驳回,使得有驳回的督案单也要能够添加新的督案单)
	 * @param caseid
	 * @return
	 */
	@Query(value = "select * from t_caselist where case_id = ?1 and caselist_status = 1 and state != 5", nativeQuery = true)
    CaseList findByCaseid(String caseid);

}
