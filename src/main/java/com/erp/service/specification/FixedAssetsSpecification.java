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

import com.erp.dto.filter.FixedassetsFilterDto;
import com.erp.orm.domain.Fixedassets;
import com.erp.orm.domain.Fixedassets_;

public class FixedAssetsSpecification implements Specification<Fixedassets> {
    private FixedassetsFilterDto filterDto;

    public FixedAssetsSpecification(FixedassetsFilterDto filterDto) {
        this.filterDto = filterDto;
    }

    public Predicate toPredicate(Root<Fixedassets> root,
            CriteriaQuery<?> query, CriteriaBuilder cb) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date olddate = null;
        Date nowdate = null;
        try {
            olddate = df.parse("1970-01-01");
            nowdate = df.parse(df.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Predicate predicate = null;
        if (null != filterDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();
            // 编号
            String assetsNo = filterDto.getAssetsNo();
            if (StringUtils.isNotBlank(assetsNo)) {
                Path<String> column = root.get(Fixedassets_.assetsNo);
                whereClauses.add(cb.equal(column, assetsNo));
            }
            // 固定资产名字
            String assetsName = filterDto.getAssetsName();
            if (StringUtils.isNotBlank(assetsName)) {
                Path<String> column = root.get(Fixedassets_.assetsName);
                whereClauses.add(cb.like(column, "%" + assetsName + "%"));
            }
            // 型号
            String style = filterDto.getStyle();
            if (StringUtils.isNotBlank(style)) {
                Path<String> column = root.get(Fixedassets_.style);
                whereClauses.add(cb.like(column, "%" + style + "%"));
            }
            // 状态   
            String status = filterDto.getStatus();
            if (StringUtils.isNotBlank(status) && !status.equals("0")) {
                Path<Integer> column = root.get(Fixedassets_.status);
                whereClauses.add(cb.equal(column, status));
            }
            
            //使用部门
            String useDepartment = filterDto.getUseDepartment();
            if (StringUtils.isNotBlank(useDepartment)) {
                Path<String> column = root.get(Fixedassets_.useDepartment);
                whereClauses.add(cb.equal(column, useDepartment));
            }
            
           //使用人
            String use_person = filterDto.getUsePerson();
            if (StringUtils.isNotBlank(use_person)) {
                Path<String> column = root.get(Fixedassets_.use_person);
                whereClauses.add(cb.equal(column, use_person));
            }

            // 时间
            Date startTime = filterDto.getStartTime();
            Date endTime = filterDto.getEndTime();
            if ((null != startTime && !"".equals(startTime))
                    && (null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Fixedassets_.startTime),
                        startTime, endTime));
            } else if ((null != startTime && !"".equals(startTime))) {
                whereClauses.add(cb.between(root.get(Fixedassets_.startTime),
                        startTime, nowdate));
            } else  if ((null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Fixedassets_.startTime),olddate, endTime));
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