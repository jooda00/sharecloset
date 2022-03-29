package com.saveearth.closet.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@Table(name = "item_like")
// 일단 item 테이블하고 연동 끊어놓기
public class ItemLike {

    @Id @GeneratedValue
    @Column(name = "item_like_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private Item item;

    // 좋아요 유무
    private Boolean steam;
}
