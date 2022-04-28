package com.thedebuggers.backend.controller;

import com.thedebuggers.backend.auth.ELUserDetails;
import com.thedebuggers.backend.domain.entity.Comment;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.CommentReqDto;
import com.thedebuggers.backend.service.CommentService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "댓글 관련 API", tags = "comment")
@Slf4j
@RequestMapping("/api/v1/community/{communityNo}/post/{postNo}/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @ApiOperation(value = "댓글 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    private ResponseEntity<List<Comment>> getCommentList(@ApiParam(defaultValue = "1") @PathVariable long postNo) {
        List<Comment> commentList = commentService.getCommentList(postNo);
        return ResponseEntity.ok(commentList);
    }

    @PostMapping
    @ApiOperation(value = "댓글 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    private ResponseEntity<String> registComment(@ApiParam(defaultValue = "1") @PathVariable long postNo, @RequestBody CommentReqDto commentDto, Authentication authentication) {
        ELUserDetails userDetails = (ELUserDetails) authentication.getDetails();
        User user = userDetails.getUser();

        try {
            commentService.registComment(postNo, user, commentDto);
        }catch (Exception e){
            return ResponseEntity.ok("Failed");
        }
        return ResponseEntity.ok("Success");
    }
}
