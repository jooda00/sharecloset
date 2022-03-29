package com.saveearth.closet.repository;

import com.saveearth.closet.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // 시간 순 조회 SQL
    @Query(value = "select * from item i order by i.date desc",nativeQuery = true)
    public List<Item> findAllByOrderByIdAsc();

    // 유저 별 게시글 목록 조회
    @Query(value = "select * from item i where i.user_id = :userId",nativeQuery = true)
    public List<Item> findAllByUser(@Param("userId") Long userId);

    // 카테고리 별 게시글 목록 조회
    @Query(value = "select * from item i where i.category = :cId",nativeQuery = true)
    public List<Item> findByCategory(@Param("cId") int cId);
}
