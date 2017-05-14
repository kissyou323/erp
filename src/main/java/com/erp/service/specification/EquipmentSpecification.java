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

import com.erp.dto.EquipmentDto;
import com.erp.orm.domain.Equipment;
import com.erp.orm.domain.Equipment_;
import com.erp.util.date.DateUtil;

/**
 * 
 *
 * @author 王建观
 * @since 2016年5月1日01:39:37
 */
public class EquipmentSpecification implements Specification<Equipment> {

    private EquipmentDto equipmentDto;

    public EquipmentSpecification(EquipmentDto equipmentDto) {
        this.equipmentDto = equipmentDto;
    }

    public Predicate toPredicate(Root<Equipment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        if (null != equipmentDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();
         // 编号
            String EquipmentNo = equipmentDto.getEquipmentNo();
            if (StringUtils.isNotBlank(EquipmentNo)) {
                Path<String> column = root.get(Equipment_.equipmentNo);
                whereClauses.add(cb.equal(column, EquipmentNo));
            }
            // 固定资产名字
            String equipmentName = equipmentDto.getEquipmentName();
            if (StringUtils.isNotBlank(equipmentName)) {
                Path<String> column = root.get(Equipment_.equipmentName);
                whereClauses.add(cb.like(column, "%" + equipmentName + "%"));
            }
            // 型号规格
            String style = equipmentDto.getStyle();
            if (StringUtils.isNotBlank(style)) {
                Path<String> column = root.get(Equipment_.style);
                whereClauses.add(cb.like(column, "%" + style + "%"));
            }
            // 状态   
            String status = equipmentDto.getStatus();
            if (StringUtils.isNotBlank(status) && !status.equals("0")) {
                Path<String> column = root.get(Equipment_.status);
                whereClauses.add(cb.equal(column, status));
            }

            // 时间
            String startTime = equipmentDto.getStartTime();
            String endTime = equipmentDto.getEndTime();
            
            if ((null != startTime && !"".equals(startTime))
                    && (null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Equipment_.startTime),
                        DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS),DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS)));
            } else if ((null != startTime && !"".equals(startTime))) {
                whereClauses.add(cb.between(root.get(Equipment_.startTime),
                        DateUtil.parse(startTime, DateUtil.YYYYMMDDHHMMSS), nowdate));
            } else  if ((null != endTime && !"".equals(endTime))) {
                whereClauses.add(cb.between(root.get(Equipment_.startTime),olddate, DateUtil.parse(endTime, DateUtil.YYYYMMDDHHMMSS)));
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
