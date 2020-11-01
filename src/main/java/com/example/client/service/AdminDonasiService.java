package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminDonasiRepository;
import com.example.client.model.Donasi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminDonasiService {
    @Autowired
	private AdminDonasiRepository repo;
	
	public List listAll() {
		return repo.findAllMember();
	}

	
	public List daftar() {
		return repo.daftarumat();
	}

	public Donasi get(long donasi_id) {
		return repo.findById(donasi_id).get();
	}

	public void save(Donasi donasi) {
		repo.save(donasi);
	}
}