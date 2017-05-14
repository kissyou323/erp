package com.erp.orm.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Possessions.class)
public class Msg_ {

	public static volatile SingularAttribute<Msg, Integer> id;
	public static volatile SingularAttribute<Msg, Integer> type;
	public static volatile SingularAttribute<Msg, String> content;
	public static volatile SingularAttribute<Msg, String> userLogin;
	public static volatile SingularAttribute<Msg, Date> startTime;

}
