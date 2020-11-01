package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminChatRepository;
import com.example.client.model.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminChatService {
    @Autowired
	private AdminChatRepository repo;
	
	public List listAll() {
		return repo.findAllMember();
	}

	public Chat get(long chat_id) {
		return repo.findById(chat_id).get();
	}

	public void save(Chat chat, long chat_id) {
		if (chat_id != 0){
			chat.setChat_id(chat_id);
			repo.save(chat);
		}else{
			repo.save(chat);
		}	
	}
}