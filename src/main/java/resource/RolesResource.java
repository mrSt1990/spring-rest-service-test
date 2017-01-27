package resource;

import org.springframework.beans.factory.annotation.Autowired;
import repository.RoleRepository;

/**
 * Created by mr_St on 27.01.17.
 */
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
