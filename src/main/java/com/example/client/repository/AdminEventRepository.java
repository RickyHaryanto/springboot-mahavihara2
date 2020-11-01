package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Event;

public interface AdminEventRepository extends JpaRepository<Event, Long> {
    @Query(
        value = "SELECT event_id,event_judul,date_format(str_to_date(event_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),event_foto,event_keterangan,event_waktu from event", 
        nativeQuery = true)
        List findAll();
}