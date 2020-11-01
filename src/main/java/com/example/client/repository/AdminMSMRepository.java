package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.MSM;

public interface AdminMSMRepository extends JpaRepository<MSM, Long> {
    @Query(
        value = "SELECT msm_id,msm_alamat,msm_bakat,date_format(str_to_date(msm_bod,'%Y-%m-%d'),'%d-%M-%Y'),msm_email,msm_foto,msm_nama,date_format(str_to_date(msm_qiudao,'%Y-%m-%d'),'%d-%M-%Y'),msm_status,user_id from msm", 
       nativeQuery = true)
       List findAll();
}