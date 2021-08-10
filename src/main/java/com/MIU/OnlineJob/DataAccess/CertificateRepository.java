package com.MIU.OnlineJob.DataAccess;

import com.MIU.OnlineJob.Models.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}

