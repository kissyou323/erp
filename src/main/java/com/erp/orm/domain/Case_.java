package com.erp.orm.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Case.class)
public abstract class Case_ {

    public static volatile SingularAttribute<Case, Integer> caseId;
    public static volatile SingularAttribute<Case, String> caseNo;
    public static volatile SingularAttribute<Case, String> caseName;
    public static volatile SingularAttribute<Case, String> policeLoginName;
    public static volatile SingularAttribute<Case, String> policeName;
    public static volatile SingularAttribute<Case, Date> caseTime;
    public static volatile SingularAttribute<Case, String> caseAddress;
    public static volatile SingularAttribute<Case, Date> startTime;
    public static volatile SingularAttribute<Case, Date> end_time;
    public static volatile SingularAttribute<Case, String> caseStatus;
    public static volatile SingularAttribute<Case, String> fileStatus;
    public static volatile SingularAttribute<Case, String> qrcode;
    public static volatile SingularAttribute<Case, Date> modifyTime;
    public static volatile SingularAttribute<Case, String> modifyName;
    public static volatile SingularAttribute<Case, String> caseExplain;
    public static volatile SingularAttribute<Case, String> possessionsNo;
    public static volatile SingularAttribute<Case, String> possessionsName;
    public static volatile SingularAttribute<Case, String> style;
    public static volatile SingularAttribute<Case, String> caseAddUrl;
    public static volatile SingularAttribute<Case, String> endcaseExplain;
    public static volatile SingularAttribute<Case, String> endcaseStatus;
    public static volatile SingularAttribute<Case, String> rejectmsg;
    public static volatile SingularAttribute<Case, String> parentCaseno;
    public static volatile SingularAttribute<Case, String> sonStatus;
    
    
}