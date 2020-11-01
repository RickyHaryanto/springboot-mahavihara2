package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Chat;

public interface AdminChatRepository extends JpaRepository<Chat, Long> {
    @Query(
        value = "SELECT c.*,u.email FROM chat c INNER JOIN user u ON (c.user_id=u.user_id) order by chat_id desc", 
        nativeQuery = true)
        List findAllMember();

       
    
}