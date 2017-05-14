package com.erp.service.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.erp.dto.CaseListDto;
import com.erp.orm.domain.Case;
import com.erp.orm.domain.CaseList;
import com.erp.orm.domain.CaseList_;
import com.erp.orm.domain.Case_;

/**
 * 
 *
 * @author wjg
 * @since 2016年5月1日01:39:37
 */
public class CaseListSpecification implements Specification<CaseList> {

    private CaseListDto caseListDto;

    public CaseListSpecification(CaseListDto caseListDto) {
        this.caseListDto = caseListDto;
    }

    public Predicate toPredicate(Root<CaseList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date olddate = null;
        Date nowdate = null;
        try {
            olddate = df.parse("1970-01-01");
            nowdate = df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (null != caseListDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();
            // 编号
            String caseNo = caseListDto.getCaseNo();
            if (StringUtils.isNotBlank(caseNo)) {
                Path<String> column = root.get(CaseList_.caseNo);
                whereClauses.add(cb.equal(column, caseNo));
            }
         // 案卷编号
            String caselistNo = caseListDto.getCaselistNo();
            if (StringUtils.isNotBlank(caselistNo)) {
                Path<String> column = root.get(CaseList_.caselistNo);
                whereClauses.add(cb.equal(column, caselistNo));
            }
            
         // 民警
            String policeName = caseListDto.getPoliceName();
            if (StringUtils.isNotBlank(policeName)) {
                Path<String> column = root.get(CaseList_.policeName);
                whereClauses.add(cb.equal(column, policeName));
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
