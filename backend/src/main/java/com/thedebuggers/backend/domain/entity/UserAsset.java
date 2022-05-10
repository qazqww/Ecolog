package com.thedebuggers.backend.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    private int index;
    private int type;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
