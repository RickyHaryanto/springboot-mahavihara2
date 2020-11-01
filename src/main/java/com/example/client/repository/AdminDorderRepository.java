package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Hashtable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Dorder;

public interface AdminDorderRepository extends JpaRepository<Dorder, Long> {
    @Query(
        value = "SELECT d.*,b.barang_nama FROM dorder d INNER JOIN barang b ON (d.barang_id=b.barang_id) WHERE horder_id = ?", 
        nativeQuery = true)
        List findAllMember(String param);

        @Query(
            value = "SELECT dorder_harga from dorder where dorder_id =?", 
            nativeQuery = true)
            Integer findharga(Long param);
    
    

}
