package com.erp.orm.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;

public class Repair_ {

	public static volatile SingularAttribute<Repair, Integer> repair_id;
	public static volatile SingularAttribute<Repair, String> repair_name;
	public static volatile SingularAttribute<Repair, String> tel;
	public static volatile SingularAttribute<Repair, String> repair_explain;
	public static volatile SingularAttribute<Repair, Date> repair_time;
	public static volatile SingularAttribute<Repair, String> repair_status;
	public static volatile SingularAttribute<Repair, Integer> assets_equipment_id;
	public static volatile SingularAttribute<Repair, String> assets_equipment_name;
	public static volatile SingularAttribute<Repair, String> style;
}
