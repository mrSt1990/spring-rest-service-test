package org.spring.boot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.spring.boot.repository.RoleRepository;

/**
 * Created by mr_St on 27.01.17.
 */
public class RolesResource {

    private RoleRepository roleRepository;

    @Autowired
    public RolesResource(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
