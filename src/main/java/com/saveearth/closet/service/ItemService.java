package com.saveearth.closet.service;

import com.saveearth.closet.entity.Item;
import com.saveearth.closet.entity.ItemLike;
import com.saveearth.closet.entity.User;
import com.saveearth.closet.entity.dto.ItemDto;
import com.saveearth.closet.entity.dto.ItemLikeDto;
import com.saveearth.closet.repository.ItemLikeRepository;
import com.saveearth.closet.repository.ItemRepository;
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
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemLikeRepository itemLikeRepository;

    // 게시글 단건 조회
    public Optional<Item> findOne(Long id){
        return itemRepository.findById(id);
    }

    // 게시글 모두 조회 --> 최신 순으로
    public List<Item> findAll(){
//        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return itemRepository.findAllByOrderByIdAsc();
    }

    // 게시글 회원 소유만 보여주기 조회
    public List<Item> findByUser(Long userId){
        return itemRepository.findAllByUser(userId);
    }

    // 게시글 등록(게시글 업뎃)
    @Transactional
    public Item create(ItemDto itemDto) throws Exception{
        Optional<User> user = userRepository.findById(itemDto.getUserId());
        Item item = new Item();
        BeanUtils.copyProperties(itemDto, item);
        item.setUser(user.get());
        return itemRepository.save(item);
    }

    // 게시글 좋아요
    @Transactional
    public ItemLike createLike(ItemLikeDto likeDto){
        Optional<Item> item = itemRepository.findById(likeDto.getItemId());
        ItemLike like = new ItemLike();
        BeanUtils.copyProperties(likeDto,like);
        like.setItem(item.get());
        return itemLikeRepository.save(like);

    }
    // 게시글 삭제
    @Transactional
    public void deleteItem(Long itemId){
        itemRepository.deleteById(itemId);
    }

    // 게시글 카테고리별로 보기
    public List<Item> findByCategory(int cId){
        return itemRepository.findByCategory(cId);
    }

    // 좋아요한 게시글 보기
    public List<ItemLike> findLike(){
        return itemLikeRepository.findALL();
    }
}
