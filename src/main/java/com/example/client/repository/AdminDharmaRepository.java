package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Dharma;

public interface AdminDharmaRepository extends JpaRepository<Dharma, Long> {
    @Query(
        value = "SELECT dharma_id,dharma_isi,dharma_judul,dharma_media,date_format(str_to_date(dharma_tanggal,'%Y-%m-%d'),'%d-%M-%Y')from dharma", 
        nativeQuery = true)
        List findAll();
}