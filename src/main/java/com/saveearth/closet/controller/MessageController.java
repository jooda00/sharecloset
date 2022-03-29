package com.saveearth.closet.controller;

import com.saveearth.closet.entity.Message;
import com.saveearth.closet.entity.dto.MessageDto;
import com.saveearth.closet.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // 메세지 보내기
    // message/아이템 아이디 --> 그 아이템 가진 사람의 아이디가 receiver, body의 sender아이디가 sender(얘는 로그인 세션으로)
    @PostMapping("/message/{id}")
    public ResponseEntity<Message> save(@PathVariable("id") Long id, @RequestBody MessageDto messageDto){
        messageDto.setReceiver(messageService.findUser(id));
        messageDto.setDate(LocalDateTime.now());
        return ResponseEntity.ok(messageService.create(messageDto));
    }

    // 전체 메세지 조회
    @GetMapping("/message/{id}")
    public List<Message> findMessage(@PathVariable("id") Long id){
        return messageService.findByUserId(id);
    }

    // 보낸 사용자로 해서 메세지 조회
    // message/받은사람 아이디/보낸사람 아이디
//    @GetMapping("message/{id}/{sid}")
//    public List<MessageSpecific> findSpecificMessage(@PathVariable("id") Long id, @PathVariable("sid") Long sid){
//        return messageService.findBySenderId(id,sid);
//    }
}

