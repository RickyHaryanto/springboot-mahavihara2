package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminEventRepository;
import com.example.client.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminEventService {
    @Autowired
	private AdminEventRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public Event get(long event_id) {
		return repo.findById(event_id).get();
	}

	public void save(Event event,long event_id) {
		if (event_id != 0){
			event.setEvent_id(event_id);
			repo.save(event);
		}else{
			repo.save(event);
		}	
	}
}