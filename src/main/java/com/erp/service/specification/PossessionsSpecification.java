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

import com.erp.dto.PossessionsDto;
import com.erp.orm.domain.Possessions;
import com.erp.orm.domain.Possessions_;
import com.erp.util.date.DateUtil;

/**
 * 
 *
 * @author wjg
 * @since 2016年5月1日01:39:37
 */
public class PossessionsSpecification implements Specification<Possessions> {

    private PossessionsDto possessionsDto;

    public PossessionsSpecification(PossessionsDto possessionsDto) {
        this.possessionsDto = possessionsDto;
    }

    public Predicate toPredicate(Root<Possessions> root,
            CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = null;
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
        if (null != possessionsDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();

            String possessions_no = possessionsDto.getPossessionsNo();
            if (StringUtils.isNotBlank(possessions_no)) {
                Path<String> column = root.get(Possessions_.possessionsNo);
                whereClauses.add(cb.equal(column, possessions_no));
            }
            String possessions_name = possessionsDto.getPossessionsName();
            if (StringUtils.isNotBlank(possessions_name)) {
                Path<String> column = root.get(Possessions_.possessionsName);
                whereClauses.add(cb.like(column, "%" + possessions_name + "%"));
            }
             // 状态   
            String status = possessionsDto.getStatus();
            if (StringUtils.isNotBlank(status) && !status.equals("0")) {
                Path<String> column = root.get(Possessions_.status);
                whereClauses.add(cb.equal(column, status));
            }
         // 时间
            String startTime = possessionsDto.getStartTime();
            String endTime = possessionsDto.getEndTime();
            if ((null != startTime && !"".equals(startTime))
                    && (null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Possessions_.startTime),
                        DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS), DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS)));
            } else if ((null != startTime && !"".equals(startTime))) {
                whereClauses.add(cb.between(root.get(Possessions_.startTime),
                        DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS), nowdate));
            } else  if ((null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Possessions_.startTime),olddate,DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS) ));
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
