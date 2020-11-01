package com.example.client.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import com.example.client.repository.AdminMemberDiklatRepository;
import com.example.client.model.MemberDiklat;
import com.example.client.model.DetailJumlah;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminMemberDiklatService {
    @Autowired
	private AdminMemberDiklatRepository repo;
	
	public List listAll() {
		return repo.findAllUser();
	}

	public BigInteger listAllMember() {
		return repo.findAllMember();
	}


	public MemberDiklat get(long memberdiklat_id) {
		return repo.findById(memberdiklat_id).get();
	}

	public void save(MemberDiklat memberdiklat) {
		repo.save(memberdiklat);
	}
}