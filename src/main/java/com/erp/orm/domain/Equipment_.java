package com.erp.orm.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipment.class)
public abstract class Equipment_ {
	public static volatile SingularAttribute<Equipment,Integer> equipmentId;
	public static volatile SingularAttribute<Equipment,String> equipmentNo;
	public static volatile SingularAttribute<Equipment,String> equipmentName;
	public static volatile SingularAttribute<Equipment,String> style;
	public static volatile SingularAttribute<Equipment,String> equipmentExplain;
	public static volatile SingularAttribute<Equipment,String> keeper;
	public static volatile SingularAttribute<Equipment,Date> startTime;
	public static volatile SingularAttribute<Equipment,Date> overTime;
	public static volatile SingularAttribute<Equipment,String> status;
	public static volatile SingularAttribute<Equipment,String> qrcode;
	public static volatile SingularAttribute<Equipment,Date> modifyTime;
	public static volatile SingularAttribute<Equipment,String> modifyName;



}
