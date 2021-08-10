package com.MIU.OnlineJob.DataAccess;

import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findCompanyByUserId(Long userId);
}
