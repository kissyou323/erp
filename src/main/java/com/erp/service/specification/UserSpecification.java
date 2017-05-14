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

import com.erp.dto.filter.UserFilterDto;
import com.erp.orm.domain.Fixedassets_;
import com.erp.orm.domain.User;
import com.erp.orm.domain.User_;

/**
 * 
 *
 * @author hekui
 * @since 2015年12月11日 下午2:55:24
 */
public class UserSpecification implements Specification<User> {

    private UserFilterDto userFilterDto;

    public UserSpecification(UserFilterDto userFilterDto) {
        this.userFilterDto = userFilterDto;
    }

    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = null;
        if (null != userFilterDto) {
            List<Predicate> whereClauses = new ArrayList<Predicate>();

            // OPEN账号
            String username = userFilterDto.getUsername();
            if (StringUtils.isNotBlank(username)) {
                Path<String> column = root.get(User_.userName);
                whereClauses.add(cb.like(column, "%" + username + "%"));
            }
            String loginName = userFilterDto.getLoginName();
            if (StringUtils.isNotBlank(loginName)) {
                Path<String> column = root.get(User_.loginName);
                whereClauses.add(cb.equal(column, loginName));
            }
            
            String mobile = userFilterDto.getMobile();
            if (StringUtils.isNotBlank(mobile)) {
                Path<String> column = root.get(User_.mobile);
                whereClauses.add(cb.equal(column, mobile));
            }
            
            String roleId = userFilterDto.getRoleId();
            if (StringUtils.isNotBlank(roleId)) {
                Path<String> column = root.get(User_.roleid);
                whereClauses.add(cb.equal(column, roleId));
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
