package com.erp.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.orm.domain.Equipment;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer>, JpaSpecificationExecutor<Equipment>{

	/***
	 * 根据装备ID查找装备
	 * @param equipmentNo
	 * @return
	 */
	@Query(value = "select e.* from t_equipment e where e.equipment_id = ?1", nativeQuery = true)
	List<Equipment> findByEquipmentId(int equipmentId);
	
	
	/***
	 * 根据装备No查找装备
	 * @param equipmentNo
	 * @return
	 */
	@Query(value = "select e.* from t_equipment e where e.equipment_no = ?1", nativeQuery = true)
	List<Equipment> findByEquipmentNo(String equipmentNo);
	
	
//	@Modifying
//    @Query("update t_equipment set status=?2 where equipment_no = ?1")
//    void updateStatus(String num,String status);

}
