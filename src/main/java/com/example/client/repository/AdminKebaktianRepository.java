package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Kebaktian;

public interface AdminKebaktianRepository extends JpaRepository<Kebaktian, Long> {
    @Query(
        value = "SELECT kebaktian_id, date_format(str_to_date(kebaktian_tanggal,'%Y-%m-%d'),'%d-%M-%Y'), kebaktian_hari, kebaktian_waktu, kebaktian_pembicara from kebaktian", 
        nativeQuery = true)
        List findAll();
}