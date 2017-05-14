package com.erp.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.erp.dto.SuspectDto;
import com.erp.orm.domain.Suspect;
import com.erp.orm.domain.Suspect_;

public class SuspectSpecification implements Specification<Suspect> {

    private SuspectDto suspectDto;

    public SuspectSpecification(SuspectDto suspectDto) {
        this.suspectDto = suspectDto;
    }

    @Override
    public Predicate toPredicate(Root<Suspect> root, CriteriaQuery<?> query,
            CriteriaBuilder cb) {
        Predicate predicate = null;

        if (null != suspectDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();
            
         // 案件号码编号
            String caseNo = suspectDto.getCaseNo();
            if (StringUtils.isNotBlank(caseNo)) {
                Path<String> column = root.get(Suspect_.caseNo);
                whereClauses.add(cb.equal(column, caseNo));
            }
            
            
            
            // 组成where子句
            if (!whereClauses.isEmpty()) {
                Predicate[] predicates = new Predicate[whereClauses.size()];
                predicate = cb.and(whereClauses.toArray(predicates));
            }

        }

        return predicate;
    }

}
