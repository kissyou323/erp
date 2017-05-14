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

import com.erp.dto.RecordDto;
import com.erp.dto.filter.RecordFilterDto;
import com.erp.orm.domain.Record;
import com.erp.orm.domain.Record_;
import com.erp.orm.domain.Repair_;

/**
 * 
 *
 * @author wjg
 * @since 2016年5月1日01:39:37
 */
public class RecordSpecification implements Specification<Record> {

    private RecordDto recordDto;

    public RecordSpecification(RecordDto recordDto) {
        this.recordDto = recordDto;
    }

    public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = null;
        if (null != recordDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();
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
            
         // 时间
            Date startTime = recordDto.getStartTime();
            Date endTime = recordDto.getEndTime();
            if ((null != startTime && !"".equals(startTime))
                    && (null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Record_.recordTime),
                        startTime, endTime));
            } else if ((null != startTime && !"".equals(startTime))) {
                whereClauses.add(cb.between(root.get(Record_.recordTime),
                        startTime, nowdate));
            } else  if ((null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Record_.recordTime),olddate, endTime));
            }
            
            String recordName = recordDto.getRecordName();
            if (StringUtils.isNotBlank(recordName)) {
                Path<String> column = root.get(Record_.recordName);
                whereClauses.add(cb.like(column, "%" + recordName + "%"));
            }
            
            String recordNo = recordDto.getRecordNo();
            if (StringUtils.isNotBlank(recordNo)) {
                Path<String> column = root.get(Record_.recordNo);
                whereClauses.add(cb.equal(column, recordNo));
            }
            
            String style = recordDto.getStyle();
            if (StringUtils.isNotBlank(style)) {
                Path<String> column = root.get(Record_.style);
                whereClauses.add(cb.equal(column, style));
            }
            String recordStyle = recordDto.getRecordStyle();
            if (StringUtils.isNotBlank(recordStyle)) {
                Path<String> column = root.get(Record_.recordStyle);
                whereClauses.add(cb.equal(column, recordStyle));
            }
            String status = recordDto.getStatus();
            if (StringUtils.isNotBlank(status)) {
                Path<String> column = root.get(Record_.status);
                whereClauses.add(cb.equal(column, status));
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
