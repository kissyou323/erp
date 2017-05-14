package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.Possessions;

public interface PossessionsRepository extends CrudRepository<Possessions, Integer>,JpaSpecificationExecutor<Possessions> {

	@Query(value = "select p.* from t_possessions p where p.possessions_no = ?1", nativeQuery = true)
	List<Possessions> findByPossessionsNo(String possessionsNo);

	
//	@Modifying
//    @Query("update t_possessions set status=?2 where possessions_no = ?1")
//    void updateStatus(String num,String status);
	
	/**
	 * 查询出最后修改时间已经大于半年了，且已经移交的收缴涉案财物
	 * @return
	 */
	@Query(value = "select * from t_possessions p where datediff(now(),p.modify_time) > 184 and p.status = 5 and possType = 1", nativeQuery = true)
    List<Possessions> getPossessionsStaut_7();
}
