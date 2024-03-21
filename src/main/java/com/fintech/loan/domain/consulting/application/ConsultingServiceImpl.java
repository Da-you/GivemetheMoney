package com.fintech.loan.domain.consulting.application;

import com.fintech.loan.domain.consulting.domain.Consulting;
import com.fintech.loan.domain.consulting.dto.ConsultingDto.ConsultingRequest;
import com.fintech.loan.domain.consulting.dto.ConsultingDto.ConsultingResponse;
import com.fintech.loan.domain.consulting.repository.ConsultingRepository;
import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultingServiceImpl implements ConsultingService {

    private final ModelMapper modelMapper;  // DTO > Entity, Entity > DTO
    private final ConsultingRepository consultingRepository;

    @Override
    public ConsultingResponse create(ConsultingRequest request) {
        Consulting con = modelMapper.map(request, Consulting.class);
        con.setAppliedAt(LocalDateTime.now());

        return modelMapper.map(consultingRepository.save(con), ConsultingResponse.class);
    }

    @Override
    public ConsultingResponse get(Long id) {
        Consulting con = consultingRepository.findById(id).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR));
        return modelMapper.map(con, ConsultingResponse.class);
    }

    @Override
    public ConsultingResponse update(Long id, ConsultingRequest request) {
        Consulting con = consultingRepository.findById(id).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR));

        con.setName(request.getName());
        con.setCellPhone(request.getCellPhone());
        con.setEmail(request.getEmail());
        con.setMemo(request.getMemo());
        con.setAddress(request.getAddress());
        con.setCity(request.getCity());
        con.setZipCode(request.getZipCode());

        return modelMapper.map(consultingRepository.save(con), ConsultingResponse.class);
    }

    @Override
    public void delete(Long id) {
        Consulting con = consultingRepository.findById(id).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR));
        con.setIsDeleted(true);
        consultingRepository.save(con);
    }
}
