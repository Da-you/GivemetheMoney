package com.fintech.loan.domain.loanApplication.application;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    void save(Long applicationId,MultipartFile file);

    Resource load(Long applicationId,String fileName);
    Stream<Path> loadAll(Long applicationId);

    void deleteAll(Long applicationId);
}
