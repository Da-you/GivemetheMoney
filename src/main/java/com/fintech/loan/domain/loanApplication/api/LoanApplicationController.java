package com.fintech.loan.domain.loanApplication.api;

import com.fintech.loan.domain.AbstractController;
import com.fintech.loan.domain.loanApplication.application.FileStorageService;
import com.fintech.loan.domain.loanApplication.application.LoanApplicationService;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.FileInfoDto;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationRequest;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationResponse;
import com.fintech.loan.global.dto.ResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class LoanApplicationController extends AbstractController {
    private final LoanApplicationService loanApplicationService;
    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseDTO<LoanApplicationResponse> create(@RequestBody LoanApplicationRequest request) {
        return ok(loanApplicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDTO<LoanApplicationResponse> get(@PathVariable Long applicationId) {
        return ok(loanApplicationService.get(applicationId));
    }

    @PatchMapping("/{applicationId}")
    public ResponseDTO<LoanApplicationResponse> update(@PathVariable Long applicationId,
                                                       @RequestBody LoanApplicationRequest request) {
        return ok(loanApplicationService.update(applicationId, request));
    }

    @DeleteMapping("/{applicationId}")
    public void delete(@PathVariable Long applicationId) {
        loanApplicationService.delete(applicationId);
    }

    @PostMapping("/{applicationId}")
    public ResponseDTO<Boolean> acceptTerms(@PathVariable Long applicationId, @RequestBody LoanApplicationDto.AcceptTermsDto request) {
        return ok(loanApplicationService.acceptTerms(applicationId, request));
    }

    @PostMapping("/files")
    public ResponseDTO<Void> save(@RequestPart MultipartFile file) throws IOException {
        fileStorageService.save(file);
        return ok();
    }

    @GetMapping("/files")
    public ResponseEntity<Resource> load(@RequestParam(name = "fileName") String fileName) {
        Resource file = fileStorageService.load(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files/info")
    public ResponseDTO<List<FileInfoDto>> getFileInfos() {

        List<FileInfoDto> result = fileStorageService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            return FileInfoDto.builder()
                    .name(fileName)
                    .url(MvcUriComponentsBuilder.fromMethodName
                            (LoanApplicationController.class, "download", fileName).build().toString()
                    ) // MVC URI 컴포넌트 발더 사용
                    .build();
        }).collect(Collectors.toList());
        return ok();
    }

    @DeleteMapping("/files")
    public ResponseDTO<Void> delete() {
        fileStorageService.deleteAll();
        return ok();
    }

}
