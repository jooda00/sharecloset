package com.saveearth.closet.controller;

import com.saveearth.closet.entity.Item;
import com.saveearth.closet.entity.User;
import com.saveearth.closet.service.ItemService;
import com.saveearth.closet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ItemService itemService;

    // 단건조회
    @GetMapping("/users/{id}")
    public Optional<User> findOne(@PathVariable("id") Long id){
        Optional<User> member = userService.findOne(id);
        return member;
    }

    // 회원 모두 조회
    @GetMapping("/users")
    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

    // 회원 각각의 게시물 조회
    @GetMapping("/users/items/{id}")
    public List<Item> findByMember(@PathVariable("id") Long id){
        List<Item> items = itemService.findByUser(id);
        return items;
    }
}
