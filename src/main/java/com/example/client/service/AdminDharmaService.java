package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminDharmaRepository;
import com.example.client.model.Dharma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminDharmaService {
    @Autowired
	private AdminDharmaRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public Dharma get(long dharma_id) {
		return repo.findById(dharma_id).get();
	}


	public void save(Dharma dharma,long dharma_id) {
		if (dharma_id != 0){
			dharma.setDharma_id(dharma_id);
			repo.save(dharma);
		}else{
			repo.save(dharma);
		}	
	}
}