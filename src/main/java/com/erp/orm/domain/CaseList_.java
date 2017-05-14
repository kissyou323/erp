package com.erp.orm.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CaseList.class)
public abstract class CaseList_ {

    public static volatile SingularAttribute<CaseList, Integer> id;
    public static volatile SingularAttribute<CaseList, Integer> case_id;
    public static volatile SingularAttribute<CaseList, String> caselistNo;
    public static volatile SingularAttribute<CaseList, String> caseNo;
    public static volatile SingularAttribute<CaseList, String> policeName;
    public static volatile SingularAttribute<CaseList, String> suggest;
    public static volatile SingularAttribute<CaseList, Date> caseendTime;
    public static volatile SingularAttribute<CaseList, String> audit;
    public static volatile SingularAttribute<CaseList, String> possessionsExplain;
    public static volatile SingularAttribute<CaseList, String> reason;
    public static volatile SingularAttribute<CaseList, String> state;
    public static volatile SingularAttribute<CaseList, String> remarks;
    public static volatile SingularAttribute<CaseList, String> url;
    public static volatile SingularAttribute<CaseList, String> caselistStatus;
}