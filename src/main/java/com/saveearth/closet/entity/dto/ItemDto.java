package com.saveearth.closet.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ItemDto{
    private Long userId;
    private String name;
    private String image;
    private String imagepath;
    private int category;
    private int size;
    private int price;
    private LocalDateTime date;

    // T/F로
    private Boolean isDirected;
    private Boolean isSended;
    private Boolean isCompleted;

}

