package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Barang;

public interface AdminBarangRepository extends JpaRepository<Barang, Long> {
    @Query(
        value = "SELECT barang_id,barang_foto,FORMAT(barang_harga,0),barang_keterangan,barang_nama FROM barang", 
        nativeQuery = true)
        List findAll();
}