package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.SMN;

public interface AdminSMNRepository extends JpaRepository<SMN, Long> {
    @Query(
        value = "SELECT s.smn_id,s.smn_dharma,s.smn_etika,s.smn_keterangan,s.smn_ritual,date_format(str_to_date(s.smn_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),s.user_id,u.email FROM smn s INNER JOIN user u ON (s.user_id=u.user_id)", 
        nativeQuery = true)
        List findAllMember();

        @Query(
            value = "SELECT u.* from user u, role r, users_roles ur where ur.user_id = u.user_id and ur.role_id = r.id and r.name='Umat'", 
            nativeQuery = true)
            List daftarumat();
}