package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminMSMRepository;
import com.example.client.model.MSM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminMSMService {
    @Autowired
	private AdminMSMRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public MSM get(long msm_id) {
		return repo.findById(msm_id).get();
	}

	public void save(MSM msm) {
		repo.save(msm);
	}
}