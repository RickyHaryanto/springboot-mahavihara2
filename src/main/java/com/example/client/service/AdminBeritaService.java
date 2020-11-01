package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminBeritaRepository;
import com.example.client.model.Berita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminBeritaService {
    @Autowired
	private AdminBeritaRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public Berita get(long berita_id) {
		return repo.findById(berita_id).get();
	}

	public void save(Berita berita,long berita_id) {
		if (berita_id != 0){
			berita.setBerita_id(berita_id);
			repo.save(berita);
		}else{
			repo.save(berita);
		}	
	}
}