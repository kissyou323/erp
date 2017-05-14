package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.Fixedassets;


public interface FixedassetsRepository extends CrudRepository<Fixedassets, Integer>, JpaSpecificationExecutor<Fixedassets> {

    @Query(value = "select f.* from t_fixedassets f where f.assets_no = ?1", nativeQuery = true)
    List<Fixedassets> findByAssetsNo(String assetsNo);

//    @Modifying
//    @Query("update t_fixedassets tf set tf.status=?1 where tf.assets_no = ?2 ")
//    void updateStatus(String status,String assets_no);
}
