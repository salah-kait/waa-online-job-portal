package com.MIU.OnlineJob.Services;

import com.MIU.OnlineJob.Repositories.RoleRepository;
import com.MIU.OnlineJob.Exception.AppException;
import com.MIU.OnlineJob.Models.Role;
import com.MIU.OnlineJob.Models.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(RoleName role){
        return roleRepository.findByName(role)
                .orElseThrow(() -> new AppException("User Role not set."+RoleName.ROLE_ADMIN));
    }
}
