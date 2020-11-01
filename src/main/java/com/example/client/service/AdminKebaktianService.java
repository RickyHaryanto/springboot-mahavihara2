
package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminKebaktianRepository;
import com.example.client.model.Kebaktian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminKebaktianService {

	@Autowired
	private AdminKebaktianRepository repo;
	
	public void save(Kebaktian kebaktian, Long kebaktian_id) {
		if (kebaktian_id != 0){
			kebaktian.setKebaktian_id(kebaktian_id);
			repo.save(kebaktian);
		}else{
			repo.save(kebaktian);
		}	
	}

	public Kebaktian get(long kebaktian_id) {
		return repo.findById(kebaktian_id).get();
	}

	public List listAll() {
		return repo.findAll();
	}

}
