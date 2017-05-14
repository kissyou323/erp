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

import com.erp.dto.MsgDto;
import com.erp.dto.filter.FixedassetsFilterDto;
import com.erp.orm.domain.Fixedassets;
import com.erp.orm.domain.Fixedassets_;
import com.erp.orm.domain.Msg;
import com.erp.orm.domain.Msg_;

public class MsgSpecification implements Specification<Msg> {
    private MsgDto msgDto;

    public MsgSpecification(MsgDto msgDto) {
        this.msgDto = msgDto;
    }

    public Predicate toPredicate(Root<Msg> root,
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
        if (null != msgDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();
            // 编号
            Integer type = msgDto.getType();
            if (null != type && type != 0) {
                Path<Integer> column = root.get(Msg_.type);
                whereClauses.add(cb.equal(column, type));
            }
            // 固定资产名字
            String username = msgDto.getUserLogin();
            if (StringUtils.isNotBlank(username)) {
                Path<String> column = root.get(Msg_.userLogin);
                whereClauses.add(cb.equal(column, username));
            }

            // 时间
            Date startTime = msgDto.getStartTime();
            Date endTime = msgDto.getEndTime();
            if ((null != startTime && !"".equals(startTime))
                    && (null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Msg_.startTime),
                        startTime, endTime));
            } else if ((null != startTime && !"".equals(startTime))) {
                whereClauses.add(cb.between(root.get(Msg_.startTime),
                        startTime, nowdate));
            } else  if ((null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Msg_.startTime),olddate, endTime));
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