package com.example.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.client.model.User;

@Repository
public interface AdminUserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Query(
        value = "SELECT user_id,email,nama,date_format(str_to_date(bod,'%Y-%m-%d'),'%d-%M-%Y'),alamat,date_format(str_to_date(qiudao,'%Y-%m-%d'),'%d-%M-%Y'),status,statusberita,kota FROM user order by user_id desc", 
        nativeQuery = true)
        List  findAllMember();
}