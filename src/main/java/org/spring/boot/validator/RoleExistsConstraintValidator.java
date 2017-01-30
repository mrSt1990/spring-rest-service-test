package org.spring.boot.validator;

import org.spring.boot.entity.Role;
import org.spring.boot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Role validator
 * Created by Aleksey Stoyokha on 29.01.17.
 */
public class RoleExistsConstraintValidator implements ConstraintValidator<RoleExists, Set<Role>> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void initialize(RoleExists annotation) {

    }

    @Override
    public boolean isValid(Set<Role> roles, ConstraintValidatorContext constraintValidatorContext) {
        if (roles == null) {
            return true;
        }

        List<Long> roleIds = new ArrayList<Long>();
        for (Role role : roles) {
            roleIds.add(role.getId());
        }

        if (roleRepository == null) {
            throw new Error("!!!");
        }

        int roleCnt = roleRepository.findAll(roleIds).size();
        return roleCnt == roleIds.size();
    }

}
