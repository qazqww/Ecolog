package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.TrashCanReqDto;
import org.locationtech.jts.io.ParseException;
import org.springframework.web.multipart.MultipartFile;

public interface TrashCanService {
    boolean registTrashCan(TrashCanReqDto trashCanReqDto, MultipartFile imageFile, User user) throws ParseException;
}
