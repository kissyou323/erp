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

import com.erp.dto.RepairDto;
import com.erp.orm.domain.Fixedassets_;
import com.erp.orm.domain.Repair;
import com.erp.orm.domain.Repair_;
import com.erp.util.date.DateUtil;

/**
 * 
 *
 * @author wjg
 * @since 2016年5月1日01:39:37
 */
public class RepairSpecification implements Specification<Repair> {

    private RepairDto repairDto;

    public RepairSpecification(RepairDto repairDto) {
        this.repairDto = repairDto;
    }

    public Predicate toPredicate(Root<Repair> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = null;
        if (null != repairDto) {
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
            // 资产装备名称
            String assets_equipment_name = repairDto.getAssets_equipment_name();
            if (StringUtils.isNotBlank(assets_equipment_name)) {
                Path<String> column = root.get(Repair_.assets_equipment_name);
                whereClauses.add(cb.like(column, "%" + assets_equipment_name + "%"));
            }
            
            String style = repairDto.getStyle();
            if (StringUtils.isNotBlank(style)) {
                Path<String> column = root.get(Repair_.style);
                whereClauses.add(cb.equal(column, style));
            }
            
            String repair_status = repairDto.getRepair_status();
            if (StringUtils.isNotBlank(repair_status)) {
                Path<String> column = root.get(Repair_.repair_status);
                whereClauses.add(cb.equal(column, repair_status));
            }
            
            String repair_name = repairDto.getRepair_name();
            if (StringUtils.isNotBlank(style)) {
                Path<String> column = root.get(Repair_.repair_name);
                whereClauses.add(cb.like(column, "%" + repair_name + "%"));
            }
            
            
         // 时间
            String startTime = repairDto.getStartTime();
            String endTime = repairDto.getEndTime();
            if ((null != startTime && !"".equals(startTime))
                    && (null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Repair_.repair_time),
                        DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS), DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS)));
            } else if ((null != startTime && !"".equals(startTime))) {
                whereClauses.add(cb.between(root.get(Repair_.repair_time),
                        DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS), nowdate));
            } else  if ((null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Repair_.repair_time),olddate, DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS)));
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
