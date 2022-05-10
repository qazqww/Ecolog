package com.thedebuggers.backend.service;

import com.thedebuggers.backend.common.exception.CustomException;
import com.thedebuggers.backend.common.util.ErrorCode;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.repository.AssetRepository;
import com.thedebuggers.backend.dto.MyAssetResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

    AssetRepository assetRepository;

    @Override
    public MyAssetResDto getMyAssetList(User user) {
        List<Integer> avatarList;
        List<Integer> roomList;

        try {
            avatarList = assetRepository.findAllByUserNoAndType(user.getNo(), 1);
        } catch (NullPointerException e) {
            throw new CustomException(ErrorCode.CONTENT_EMPTY);
        }
        try {
            roomList = assetRepository.findAllByUserNoAndType(user.getNo(), 2);
        } catch (NullPointerException e) {
            throw new CustomException(ErrorCode.CONTENT_EMPTY);
        }

        MyAssetResDto myAssetResDto = MyAssetResDto.builder()
                .avatarList(avatarList)
                .roomList(roomList)
                .build();

        return myAssetResDto;
    }
}
