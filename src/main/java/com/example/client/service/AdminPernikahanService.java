package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminPernikahanRepository;
import com.example.client.model.Pernikahan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminPernikahanService {
    @Autowired
	private AdminPernikahanRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public Pernikahan get(long pernikahan_id) {
		return repo.findById(pernikahan_id).get();
	}

	public void save(Pernikahan pernikahan) {
		repo.save(pernikahan);
	}
}