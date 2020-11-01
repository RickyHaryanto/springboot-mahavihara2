package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminSMNRepository;
import com.example.client.model.SMN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminSMNService {
    @Autowired
	private AdminSMNRepository repo;
	
	public List listAll() {
		return repo.findAllMember();
	}

	public SMN get(long smn_id) {
		return repo.findById(smn_id).get();
	}

	public void save(SMN smn, Long smn_id) {
		if (smn_id != 0){
			smn.setSmn_id(smn_id);
			repo.save(smn);
		}else{
			repo.save(smn);
		}	
	}
}