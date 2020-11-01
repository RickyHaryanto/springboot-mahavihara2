package com.example.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.DonasiUang;

public interface AdminDonasiUangRepository extends JpaRepository<DonasiUang, Long> {
    @Query(
        value = "SELECT d.donasiuang_id,FORMAT(d.donasiuang_nominal,0),d.donasiuang_nomornota,date_format(str_to_date(d.donasiuang_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),d.user_id,d.donasiuang_nama,d.donasiuang_email,u.email FROM donasi_uang d INNER JOIN user u ON (d.user_id=u.user_id)", 
        nativeQuery = true)
        List findAllMember();
}