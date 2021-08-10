package com.MIU.OnlineJob.Repositories;

import com.MIU.OnlineJob.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findCompanyByUserId(Long userId);
}
