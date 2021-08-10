package com.MIU.OnlineJob.Services;

import com.MIU.OnlineJob.DataAccess.CertificateRepository;
import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {
    private CertificateRepository certificateRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> findAll() {
        return certificateRepository.findAll();
    }

    public Certificate findById(long id) throws ResourceNotFoundException {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate", "ID", id));
    }

    public Certificate replaceCertificate(Certificate newCertificate, long id) {
        return certificateRepository
                .findById(id)
                .map(certificate -> {
                    certificate.setSerialNumber(newCertificate.getSerialNumber());
                    certificate.setExpirationDate(newCertificate.getExpirationDate());
                    certificate.setIssueDate(newCertificate.getIssueDate());
                    certificate.setIssuedBy(newCertificate.getIssuedBy());
                    certificate.setName(newCertificate.getName());
                    return this.certificateRepository.save(certificate);
                })
                .orElseGet(() -> {
                    newCertificate.setId(id);
                    return this.certificateRepository.save(newCertificate);
                });
    }

    public Certificate save(Certificate certificate) {
        certificateRepository.save(certificate);
        return certificate;
    }

    public void delete(long id) {
        certificateRepository.deleteById(id);
    }

}
