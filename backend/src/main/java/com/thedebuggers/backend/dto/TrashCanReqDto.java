package com.thedebuggers.backend.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TrashCanReqDto {

    private long no;

    private String address;
    private double lat;
    private double lng;

}
