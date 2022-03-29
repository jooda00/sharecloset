package com.saveearth.closet.controller;

import com.saveearth.closet.entity.Item;
import com.saveearth.closet.entity.ItemLike;
import com.saveearth.closet.entity.dto.ItemDto;
import com.saveearth.closet.entity.dto.ItemLikeDto;
import com.saveearth.closet.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private LocalDateTime dateTime;
    // 게시글 단건 조회
    @GetMapping("/items/{id}")
    public Optional<Item> findOne(@PathVariable("id") Long id){
        Optional<Item> item = itemService.findOne(id);
        return item;
    }

    // 게시글 시간 순서 대로 빠른게 먼저 오도록 조회
    @GetMapping("/items")
    public List<Item> findAll(){
        List<Item> items = itemService.findAll();
        return items;
    }

    // 게시글 카테고리별로 조회
    @GetMapping("/items/category/{id}")
    public List<Item> categoryFind(@PathVariable("id") int id){
        List<Item> categoryitem = itemService.findByCategory(id);
        return categoryitem;
    }

    // 게시글 등록(게시글 업뎃)
    @PostMapping(value = "/items")
    public ResponseEntity<Item> save(@RequestBody ItemDto item, MultipartFile file) throws Exception{
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(path,fileName);
        file.transferTo(saveFile);
        item.setImage(fileName);
        item.setImagepath("/files/");
        item.setDate(LocalDateTime.now());
        return ResponseEntity.ok(itemService.create(item));
    }

    // 게시글 삭제
    @GetMapping("/items/delete/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    // 게시글 좋아요
    @PostMapping("items/like/{id}")
    public ResponseEntity<ItemLike> likeItem(@PathVariable("id") Long id, @RequestBody ItemLikeDto likeDto){
        likeDto.setSteam(Boolean.TRUE);
        likeDto.setItemId(id);
        return ResponseEntity.ok(itemService.createLike(likeDto));
    }

    // 좋아요한 게시글 조회
    @GetMapping("items/like")
    public List<ItemLike> likeItem(){
        List<ItemLike> like = itemService.findLike();
        return like;
    }
}