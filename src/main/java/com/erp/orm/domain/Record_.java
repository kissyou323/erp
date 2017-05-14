package com.erp.orm.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Record.class)
public class Record_ {
	public static volatile SingularAttribute<Record, Integer> recordId;
	public static volatile SingularAttribute<Record, String> recordNo;
	public static volatile SingularAttribute<Record, String> recordStyle;
	public static volatile SingularAttribute<Record, String> recordName;
	public static volatile SingularAttribute<Record, Date> recordTime;
	public static volatile SingularAttribute<Record, String> useDepartment;
	public static volatile SingularAttribute<Record, String> agent;
	public static volatile SingularAttribute<Record, String> useExplain;
	public static volatile SingularAttribute<Record, String> status;
	public static volatile SingularAttribute<Record, String> style;

}
