package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.erp.orm.domain.Msg;

public interface MsgRepository extends CrudRepository<Msg, Integer>, JpaSpecificationExecutor<Msg>{

	@Query(value = "select * from t_msg where user_login = ?1 and type = ?2", nativeQuery = true)
	List<Msg> findMsgByUserLoginOrType( String userLogin, int type);

	
}