package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.TrashCan;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.repository.TrashCanRepository;
import com.thedebuggers.backend.dto.TrashCanReqDto;

import jdk.jpackage.internal.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class TrashCanServiceImpl implements TrashCanService{

    private final TrashCanRepository trashCanRepository;
    private final S3Service s3Service;

    @Override
    public boolean registTrashCan(TrashCanReqDto trashCanReqDto, MultipartFile imageFile, User user) throws ParseException {

//        GeometryFactory geometryFactory = new GeometryFactory();
//        Coordinate coordinate = new Coordinate(trashCanReqDto.getLat(), trashCanReqDto.getLng());
//        Point point = geometryFactory.createPoint(coordinate);

        String pointWKT = String.format("POINT(%s %s)", trashCanReqDto.getLng(), trashCanReqDto.getLat());
        Point point = (Point) new WKTReader().read(pointWKT);

//        Point point = new Point(new Coordinate(trashCanReqDto.getLng(),trashCanReqDto.getLat()));

        log.info(point.toString());

        String imageUrl = null;
        if (imageFile != null) {
            imageUrl = s3Service.upload(imageFile);
        }

        TrashCan trashCan = TrashCan.builder()
                .location(point)
                .address(trashCanReqDto.getAddress())
                .image(imageUrl)
                .user(user)
                .build();

        TrashCan trashCan1 = trashCanRepository.save(trashCan);

        log.info("trashcan : {}", trashCan1.getLocation());

        return true;
    }
}
