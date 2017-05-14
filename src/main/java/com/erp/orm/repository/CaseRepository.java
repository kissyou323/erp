package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.Case;

public interface CaseRepository extends CrudRepository<Case, Integer>, JpaSpecificationExecutor<Case>{

	@Query(value = "select e.* from t_case e where e.case_no = ?1 and e.son_status = 2", nativeQuery = true)
	List<Case> findByCaseNo(String caseId);
	
	@Query(value = "select e.* from t_case e where e.case_no = ?1", nativeQuery = true)
    List<Case> getCaseByCaseNo(String caseId);
	
	@Query(value = "select e.* from t_case e where e.parent_caseno = ?1", nativeQuery = true)
    List<Case> findParentNo(String caseNo);
	
//	@Modifying
//    @Query("update t_case set file_status=?2 where case_no = ?1 and son_status = 2")
//    void updateStatus(String num,String status);
	
	/**
	 * 当前时间减去入库时间等于22的代表着入库至今已经有23三天了
	 * 查出未结案的行政案件在第23天通知民警
	 * @return
	 */
	@Query(value = "SELECT * FROM t_case where style = 1 and case_status = 1 and datediff(now(),start_time) > 22", nativeQuery = true)
	List<Case> getEndCase_23();
}
