package com.saveearth.closet.repository;

import com.saveearth.closet.entity.ItemLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike,Long> {

    @Query(value = "select * from item_like", nativeQuery = true)
    public List<ItemLike> findALL();
}