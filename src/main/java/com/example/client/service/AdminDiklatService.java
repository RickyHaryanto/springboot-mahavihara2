
package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminDiklatRepository;
import com.example.client.model.Diklat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminDiklatService {

	@Autowired
	private AdminDiklatRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public Diklat get(long diklat_id) {
		return repo.findById(diklat_id).get();
	}

	public void save(Diklat diklat,long diklat_id) {
		if (diklat_id != 0){
			diklat.setDiklat_id(diklat_id);
			repo.save(diklat);
		}else{
			repo.save(diklat);
		}	
	}
	
}
