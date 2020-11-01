package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminNotifRepository;
import com.example.client.model.Notif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminNotifService {
    @Autowired
	private AdminNotifRepository repo;
	
	public void save(Notif notif) {
		repo.save(notif);
	}

	public Notif get(long notif_id) {
		return repo.findById(notif_id).get();
	}

	public List<Notif> listAll() {
		return repo.findAll();
	}
}