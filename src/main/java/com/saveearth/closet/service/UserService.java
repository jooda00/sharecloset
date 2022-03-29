package com.saveearth.closet.service;

import com.saveearth.closet.entity.User;
import com.saveearth.closet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 단건 조회
    public Optional<User> findOne(Long memberId){
        return userRepository.findById(memberId);
    }

    // 전체 회원 조회
    public List<User> findAll(){
        return userRepository.findAll();
    }
}