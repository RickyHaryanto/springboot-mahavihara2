package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Pernikahan;

public interface AdminPernikahanRepository extends JpaRepository<Pernikahan, Long> {
    @Query(
        value = "SELECT pernikahan_id ,pernikahan_fotopria ,pernikahan_fotowanita ,pernikahan_keterangan,pernikahan_namapria,pernikahan_namawanita,pernikahan_status,date_format(str_to_date(pernikahan_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),user_id,pernikahan_waktu from pernikahan", 
       nativeQuery = true)
       List findAll();
}