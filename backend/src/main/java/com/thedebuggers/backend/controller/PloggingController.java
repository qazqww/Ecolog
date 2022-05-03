package com.thedebuggers.backend.controller;

import com.thedebuggers.backend.auth.ELUserDetails;
import com.thedebuggers.backend.domain.entity.Plogging;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PloggingReqDto;
import com.thedebuggers.backend.dto.PloggingResDto;
import com.thedebuggers.backend.service.PloggingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "플로깅 관련 API", tags = "Plogging")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/plogging")
@RestController
public class PloggingController {

    private final PloggingService ploggingService;

    @PostMapping
    private ResponseEntity<PloggingResDto> registPlogging(@ApiIgnore Authentication authentication,
                                                          PloggingReqDto ploggingReqDto) {
        ELUserDetails userDetails = (ELUserDetails)authentication.getDetails();
        User user = userDetails.getUser();
        Plogging plogging = ploggingService.registPlogging(user, ploggingReqDto);
        return ResponseEntity.ok(PloggingResDto.of(plogging));

    }

}
