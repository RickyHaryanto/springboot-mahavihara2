package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminDonasiUangRepository;
import com.example.client.model.DonasiUang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminDonasiUangService {
    @Autowired
	private AdminDonasiUangRepository repo;
    
    public List listAll() {
		return repo.findAllMember();
	}
	public void save(DonasiUang donasiuang) {
		repo.save(donasiuang);
	}
}