package com.example.client.service;
import java.util.List;

import com.example.client.repository.AdminBarangRepository;
import com.example.client.model.Barang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
@Transactional
public class AdminBarangService {
    @Autowired
	private AdminBarangRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public Barang get(long barang_id) {
		return repo.findById(barang_id).get();
	}

	
	public void save(Barang barang,long barang_id) {
		if (barang_id != 0){
			barang.setBarang_id(barang_id);
			repo.save(barang);
		}else{
			repo.save(barang);
		}	
	}
}