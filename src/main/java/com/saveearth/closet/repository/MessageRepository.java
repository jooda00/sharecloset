package com.saveearth.closet.repository;

import com.saveearth.closet.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(value = "select i.user_id from item i where i.item_id = :iId", nativeQuery = true)
    public Long findByItemId(@Param("iId") Long iId);

    @Query(value = "select * from chat_message cm where cm.receiver = :uId",nativeQuery = true)
    public List<Message> findByUserId(@Param(("uId")) Long uId);

    //@Query(value = "select * from chat_message cm where cm.receiver = :rId and cm.user_id = :uId",nativeQuery = true)
    //public List<MessageSpecific> findBySenderId(@Param("rId") Long rId, @Param("uId") Long uId);
}
