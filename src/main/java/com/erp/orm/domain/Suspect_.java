package com.erp.orm.domain;

import javax.persistence.metamodel.SingularAttribute;

public class Suspect_ {

	public static volatile SingularAttribute<Suspect, Integer> id;
	public static volatile SingularAttribute<Suspect, String> suspectName;
	public static volatile SingularAttribute<Suspect, String> identity;
	public static volatile SingularAttribute<Suspect, String> mobileNo;
	public static volatile SingularAttribute<Suspect, String> remarks;
	public static volatile SingularAttribute<Suspect, String> caseNo;
}
