package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Berita;

public interface AdminBeritaRepository extends JpaRepository<Berita, Long> {
    @Query(
        value = "SELECT berita_id,berita_isi,berita_judul,berita_file,date_format(str_to_date(berita_tanggal,'%Y-%m-%d'),'%d-%M-%Y')from berita", 
        nativeQuery = true)
        List findAll();
}