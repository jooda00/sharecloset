package com.saveearth.closet.service;

import com.saveearth.closet.entity.Message;
import com.saveearth.closet.entity.User;
import com.saveearth.closet.entity.dto.MessageDto;
import com.saveearth.closet.repository.MessageRepository;
import com.saveearth.closet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // 아이템 소유한 회원 찾기
    public Long findUser(Long itemId){
        return messageRepository.findByItemId(itemId);
    }

    // 메세지 보내기
    @Transactional
    public Message create(MessageDto messageDto){
        Optional<User> user = userRepository.findById(messageDto.getSender());
        Message message = new Message();
        BeanUtils.copyProperties(messageDto,message);
        message.setSender(user.get());
        return messageRepository.save(message);
    }

    // 받은 사람 찾기
    public List<Message> findByUserId(Long receiver){
        return messageRepository.findByUserId(receiver);
    }

    // 보낸 사람 + 받은 사람 찾기
//    public List<MessageSpecific> findBySenderId(Long receiver, Long sender){
//        return messageRepository.findBySenderId(receiver,sender);
//    }
}

