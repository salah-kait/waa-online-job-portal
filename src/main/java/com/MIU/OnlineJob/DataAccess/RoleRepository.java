package com.MIU.OnlineJob.DataAccess;

import com.MIU.OnlineJob.Models.Role;
import com.MIU.OnlineJob.Models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
