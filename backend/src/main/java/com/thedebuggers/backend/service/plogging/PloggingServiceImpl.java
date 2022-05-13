package com.thedebuggers.backend.service.plogging;

import com.thedebuggers.backend.common.exception.CustomException;
import com.thedebuggers.backend.common.util.ErrorCode;
import com.thedebuggers.backend.common.util.S3Service;
import com.thedebuggers.backend.domain.entity.plogging.Plogging;
import com.thedebuggers.backend.domain.entity.plogging.RankingData;
import com.thedebuggers.backend.domain.entity.user.Reward;
import com.thedebuggers.backend.domain.entity.user.User;
import com.thedebuggers.backend.domain.repository.plogging.PloggingRepository;
import com.thedebuggers.backend.domain.repository.user.UserFollowRepository;
import com.thedebuggers.backend.domain.repository.user.UserRepository;
import com.thedebuggers.backend.dto.plogging.PloggingReqDto;
import com.thedebuggers.backend.dto.plogging.PloggingResDto;
import com.thedebuggers.backend.dto.plogging.RegionProgressResDto;
import com.thedebuggers.backend.dto.user.RankingResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PloggingServiceImpl implements PloggingService {

    private final PloggingRepository ploggingRepository;
    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;

    private final S3Service s3Service;

    @Transactional
    @Override
    public PloggingResDto registPlogging(User user, PloggingReqDto ploggingReqDto, List<MultipartFile> imageFileList) {
        if (imageFileList.size() < 2) {
            throw new CustomException(ErrorCode.CONTENT_NOT_FILLED);
        }
        Map<String, String> imageUrls = s3Service.upload(imageFileList);

        Plogging plogging = Plogging.builder()
                .startedAt(ploggingReqDto.getStartedAt())
                .endedAt(ploggingReqDto.getEndedAt())
                .resultImg(imageUrls.get("result"))
                .routeImg(imageUrls.get("route"))
                .time(ploggingReqDto.getTime())
                .distance(ploggingReqDto.getDistance())
                .calories(ploggingReqDto.getCalories())
                .user(user)
                .build();

        plogging = ploggingRepository.save(plogging);

        int point = ((int) Math.round(plogging.getDistance())) * Reward.PLOGGING_REWARD.getPoint();

        userRepository.updateCoinByNo(user.getNo(), point);

        PloggingResDto ploggingResDto = PloggingResDto.of(plogging);
        return ploggingResDto;
    }

    @Override
    public List<PloggingResDto> getPloggingList(long userNo) {
        List<PloggingResDto> ploggingResDtoList = ploggingRepository.findAllByUserNo(userNo).stream().map(PloggingResDto::of).collect(Collectors.toList());
        return ploggingResDtoList;
    }

    @Override
    public PloggingResDto getPlogging(long ploggingNo) {
        Plogging plogging = ploggingRepository.findById(ploggingNo).orElseThrow(() -> new CustomException(ErrorCode.CONTENT_NOT_FOUND));
        PloggingResDto ploggingResDto = PloggingResDto.of(plogging);
        return ploggingResDto;
    }

    @Override
    public RankingResDto getMyRanking(User user) {
        List<RankingResDto> rankingResDtoList = getRanking(null, "all", "all");
        RankingResDto myRanking = null;

        int rank = 0;
        for (RankingResDto r : rankingResDtoList) {
            rank++;
            if (r.getUser().getNo() == user.getNo()) {
                try {
                    myRanking = (RankingResDto) r.clone();
                } catch (CloneNotSupportedException e) {
                    throw new CustomException(ErrorCode.BAD_REQUEST);
                }
                myRanking.setRanking(rank);
                break;
            }
        }

        if (myRanking == null)
            myRanking = RankingResDto.of(0, user, 0, 0);

        return myRanking;
    }

    @Override
    public List<RankingResDto> getRanking(User user, String period, String type) {
        List<RankingResDto> rankingList = new ArrayList<>();

        Map<String, String> dateInfo = getDate(period);
        String startDay = dateInfo.get("startDay");
        String endDay = dateInfo.get("endDay");

        if (startDay.isEmpty() || endDay.isEmpty())
            throw new CustomException(ErrorCode.BAD_REQUEST);

        switch (type) {
            case "follow":
                List<User> followList = userFollowRepository.findAllFolloweeByFollower(user);
                followList.add(user);
                ploggingRepository.getRankingByFollow(followList, startDay, endDay, RankingData.class).forEach(
                        data -> {
                            User u = userRepository.findById(data.getUserNo()).orElseThrow(() -> new CustomException(ErrorCode.CONTENT_NOT_FOUND));
                            rankingList.add(RankingResDto.of(u, data.getCnt(), data.getDist()));
                        }
                );
                break;
            case "region":
                String address = user.getAddress();
                ploggingRepository.getRankingByAddress(address, startDay, endDay, RankingData.class).forEach(
                        data -> {
                            User u = userRepository.findByNo(data.getUserNo()).orElseThrow(() -> new CustomException(ErrorCode.CONTENT_NOT_FOUND));
                            rankingList.add(RankingResDto.of(u, data.getCnt(), data.getDist()));
                        }
                );
                break;
            default:
                ploggingRepository.getRankingByTime(startDay, endDay, RankingData.class).forEach(
                        data -> {
                            User u = userRepository.findByNo(data.getUserNo()).orElseThrow(() -> new CustomException(ErrorCode.CONTENT_NOT_FOUND));
                            rankingList.add(RankingResDto.of(u, data.getCnt(), data.getDist()));
                        }
                );
                break;
        }

        return rankingList;
    }

    @Override
    public List<RegionProgressResDto> getRegionProgress(String type) {
        List<RegionProgressResDto> regionProgressResDtoList = new ArrayList<>();

        Map<String, String> dateInfo = getDate(type);
        String startDay = dateInfo.get("startDay");
        String endDay = dateInfo.get("endDay");

        if (startDay.isEmpty() || endDay.isEmpty())
            throw new CustomException(ErrorCode.BAD_REQUEST);

        ploggingRepository.getRegionProgress(startDay, endDay, RankingData.class).forEach(
                data -> regionProgressResDtoList.add(RegionProgressResDto.of(data))
        );

        return regionProgressResDtoList;
    }

    private Map<String, String> getDate(String period) {
        Map<String, String> dateInfo = new HashMap<>();
        String startDay = "20000101";

        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.add(Calendar.DATE, 1);
        String endDay = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());

        switch (period) {
            case "week":
                startDay = startOfWeek();
                endDay = endOfWeek();
                break;
            case "month":
                startDay = endDay.substring(0, 6).concat("01");
                endDay = endOfMonth();
                break;
            case "all":
                break;
            default:
                throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        dateInfo.put("startDay", startDay);
        dateInfo.put("endDay", endDay);
        return dateInfo;
    }

    private String startOfWeek() {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return dateFormat.format(calendar.getTime());
    }

    private String endOfWeek() {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.add(Calendar.DATE, 1);
        return dateFormat.format(calendar.getTime());
    }

    private String endOfMonth() {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
        calendar.add(Calendar.DATE, 1);
        return dateFormat.format(calendar.getTime());
    }
}
