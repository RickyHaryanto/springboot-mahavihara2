package com.example.client.service;

import java.util.Hashtable;
import java.util.List;

import com.example.client.repository.AdminDorderRepository;
import com.example.client.model.Dorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminDorderService {
    @Autowired
	private AdminDorderRepository repo;
	
	public void save(Dorder dorder) {
		repo.save(dorder);
	}

	public Dorder get(long horder_id) {
		return repo.findById(horder_id).get();
	}

	public Dorder getEdit(long dorder_id) {
		return repo.findById(dorder_id).get();
	}

	public List listAll(String param) {
		return repo.findAllMember(param);
	}

	public void save2(Dorder dorder, long dorder_id) {
        if (dorder_id != 0){
            //user=set(user_id);
			repo.save(dorder);
		}else{
			repo.save(dorder);
		}	
	}

}