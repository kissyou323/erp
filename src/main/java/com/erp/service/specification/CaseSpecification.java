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

import com.erp.constant.Constant;
import com.erp.dto.filter.CaseFilterDto;
import com.erp.orm.domain.Case;
import com.erp.orm.domain.Case_;

/**
 * 
 *
 * @author wjg
 * @since 2016年5月1日01:39:37
 */
public class CaseSpecification implements Specification<Case> {

    private CaseFilterDto caseFilterDto;

    public CaseSpecification(CaseFilterDto caseFilterDto) {
        this.caseFilterDto = caseFilterDto;
    }

    public Predicate toPredicate(Root<Case> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        if (null != caseFilterDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();
            // 编号
            String caseNo = caseFilterDto.getCaseNo();
            if (StringUtils.isNotBlank(caseNo)) {
                Path<String> column = root.get(Case_.caseNo);
                whereClauses.add(cb.equal(column, caseNo));
            }
            
            String caseName = caseFilterDto.getCaseName();
            if (StringUtils.isNotBlank(caseName)) {
                Path<String> column = root.get(Case_.caseName);
                whereClauses.add(cb.like(column, "%" + caseName + "%"));
            }
            
            String policeLoginName = caseFilterDto.getPoliceLoginName();
            if (StringUtils.isNotBlank(policeLoginName)) {
                Path<String> column = root.get(Case_.policeLoginName);
                whereClauses.add(cb.equal(column, policeLoginName));
            }
            
            String policeName = caseFilterDto.getPoliceName();
            if (StringUtils.isNotBlank(policeName)) {
                Path<String> column = root.get(Case_.policeName);
                whereClauses.add(cb.like(column, "%" + policeName + "%"));
            }
            
            String caseStatus = caseFilterDto.getCaseStatus();
            if (StringUtils.isNotBlank(caseStatus)) {
                Path<String> column = root.get(Case_.caseStatus);
                whereClauses.add(cb.equal(column, caseStatus));
            }
            
            String fileStatus = caseFilterDto.getFileStatus();
            if (StringUtils.isNotBlank(fileStatus)) {
                Path<String> column = root.get(Case_.fileStatus);
                whereClauses.add(cb.equal(column, fileStatus));
            }
            
            String style = caseFilterDto.getStyle();
            if (StringUtils.isNotBlank(style)) {
                Path<String> column = root.get(Case_.style);
                whereClauses.add(cb.equal(column, style));
            }
            
            //案发时间
            Date caseTime_start = caseFilterDto.getCaseTime_start();
            Date caseTime_end = caseFilterDto.getCaseTime_end();
            if ((null != caseTime_start && !"".equals(caseTime_start)) && (null != caseTime_end && !"".equals(caseTime_end))) {
                whereClauses.add(cb.between(root.get(Case_.caseTime),caseTime_start, caseTime_end));
            } else if ((null != caseTime_start && !"".equals(caseTime_start))) {
                whereClauses.add(cb.between(root.get(Case_.caseTime),caseTime_start, nowdate));
            } else  if ((null != caseTime_end && !"".equals(caseTime_end))) {
                whereClauses.add(cb.between(root.get(Case_.caseTime),olddate, caseTime_end));
            }
            
            //入库时间
            Date startTime_start = caseFilterDto.getStartTime_start();
            Date startTime_end = caseFilterDto.getStartTime_end();
            if ((null != startTime_start && !"".equals(startTime_start)) && (null != startTime_end && !"".equals(startTime_end))) {
                whereClauses.add(cb.between(root.get(Case_.startTime),startTime_start, startTime_end));
            } else if ((null != startTime_start && !"".equals(startTime_start))) {
                whereClauses.add(cb.between(root.get(Case_.startTime),startTime_start, nowdate));
            } else  if ((null != startTime_end && !"".equals(startTime_end))) {
                whereClauses.add(cb.between(root.get(Case_.startTime),olddate, startTime_end));
            }
            
            //结案时间
            Date endTime_start = caseFilterDto.getEndTime_start();
            Date endTime_end = caseFilterDto.getEndTime_end();
            if ((null != endTime_start && !"".equals(endTime_start)) && (null != endTime_end && !"".equals(endTime_end))) {
                whereClauses.add(cb.between(root.get(Case_.end_time),endTime_start, endTime_end));
            } else if ((null != endTime_start && !"".equals(endTime_start))) {
                whereClauses.add(cb.between(root.get(Case_.end_time),endTime_start, nowdate));
            } else  if ((null != endTime_end && !"".equals(endTime_end))) {
                whereClauses.add(cb.between(root.get(Case_.end_time),olddate, endTime_end));
            }
            
            //   默认查询不是子案件的信息
            String sonStatus = caseFilterDto.getSonStatus();
            if (StringUtils.isNotBlank(sonStatus)) {
                Path<String> column = root.get(Case_.sonStatus);
                whereClauses.add(cb.equal(column, sonStatus));
            }
            
            //   默认查询不是子案件的信息
            String endCaseStatus = caseFilterDto.getEndcaseStatus();
            if (StringUtils.isNotBlank(endCaseStatus)) {
                Path<String> column = root.get(Case_.endcaseStatus);
                whereClauses.add(cb.equal(column, endCaseStatus));
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
