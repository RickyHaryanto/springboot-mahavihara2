package com.example.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.MemberDiklat;
import com.example.client.model.DetailJumlah;


public interface AdminMemberDiklatRepository extends JpaRepository<MemberDiklat, Long> {
    @Query(
         value = "SELECT md.memberdiklat_id,md.diklat_id,md.memberdiklat_status,date_format(str_to_date(md.memberdiklat_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),md.user_id,md.memberdiklat_email,md.memberdiklat_nama,d.diklat_nama,u.email FROM user u, diklat d, member_diklat md WHERE md.user_id = u.user_id and md.diklat_id = d.diklat_id", 
        nativeQuery = true)
        List findAllUser();

    @Query(
         value = "select count(memberdiklat_id) as jumlah from member_diklat md, diklat d where d.diklat_id = md.diklat_id and diklat_status = 'Open' and md.memberdiklat_status ='Diterima'", 
         nativeQuery = true)
         BigInteger findAllMember();
   
}