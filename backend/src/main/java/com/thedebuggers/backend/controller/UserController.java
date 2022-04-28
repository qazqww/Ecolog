package com.thedebuggers.backend.controller;

import com.thedebuggers.backend.auth.ELUserDetails;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.UserInfoResDto;
import com.thedebuggers.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "사용자 정보 관련 API", tags = {"User"})
@Slf4j
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping({"", "/", "/{userNo}"})
    @ApiOperation(value = "회원 정보 ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    private ResponseEntity<UserInfoResDto> info(@PathVariable(required = false) Long userNo, Authentication authentication) {

        User user = null;

        if (userNo == null) {
            ELUserDetails userDetails = (ELUserDetails) authentication.getDetails();
            user = userDetails.getUser();
        } else {
            user = userService.getUserByUserNo(userNo);
        }


        return ResponseEntity.ok(user != null ? UserInfoResDto.of(user) : null);
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    private ResponseEntity<String> delete(Authentication authentication) {

        ELUserDetails userDetails = (ELUserDetails) authentication.getDetails();
        User user = userDetails.getUser();

        try {
            userService.deleteUser(user);
        }catch (Exception e){
            return ResponseEntity.ok("Failed");
        }
        return ResponseEntity.ok("Success");
    }
}