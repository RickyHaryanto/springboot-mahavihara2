package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Diklat;

public interface AdminDiklatRepository extends JpaRepository<Diklat, Long> {
    @Query(
        value = "SELECT diklat_id,diklat_nama,date_format(str_to_date(diklat_start,'%Y-%m-%d'),'%d-%M-%Y'),date_format(str_to_date(diklat_end,'%Y-%m-%d'),'%d-%M-%Y'),diklat_foto,diklat_keterangan,diklat_status,date_format(str_to_date(diklat_bataswaktu,'%Y-%m-%d'),'%d-%M-%Y') from diklat", 
        nativeQuery = true)
        List findAll();

}