package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User>{
    
    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    @Query(value = "select u.* from t_user u where u.login_name = ?1", nativeQuery = true)
    List<User> findByUsername(String username);


}
