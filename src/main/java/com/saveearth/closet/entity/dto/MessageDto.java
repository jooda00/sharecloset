package com.saveearth.closet.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long sender; // 차후에 sender로 변경
    private Long receiver;
    private String message;
    private LocalDateTime date;
}

