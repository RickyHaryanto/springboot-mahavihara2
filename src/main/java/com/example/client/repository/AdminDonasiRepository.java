package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Donasi;

public interface AdminDonasiRepository extends JpaRepository<Donasi, Long> {
    @Query(
        value = "SELECT d.donasi_id,d.donasi_atasnama,d.donasi_jumlahtotal,d.donasi_namabarang,date_format(str_to_date(d.donasi_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),d.user_id,d.donasi_keterangan,u.email FROM donasi d INNER JOIN user u ON (d.user_id=u.user_id)", 
        nativeQuery = true)
        List findAllMember();
    
        @Query(
            value = "SELECT u.* from user u, role r, users_roles ur where ur.user_id = u.user_id and ur.role_id = r.id and r.name='Umat'", 
            nativeQuery = true)
            List daftarumat();
            
}
