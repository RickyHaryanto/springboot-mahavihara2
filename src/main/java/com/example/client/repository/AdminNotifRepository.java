package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Notif;

public interface AdminNotifRepository extends JpaRepository<Notif, Long> {
    
    @Query(
         value = "SELECT u.* FROM user u, role r, users_roles ur where u.user_id = ur.user_id and r.id = ur.role_id and r.name = 'Umat' and u.status='Aktif'", 
        nativeQuery = true)
        List notifdharma();

        @Query(
            value = "SELECT u.* FROM user u, role r, users_roles ur where u.user_id = ur.user_id and r.id = ur.role_id and r.name = 'Umat' and u.statusberita='Aktif'", 
           nativeQuery = true)
           List notifberita();

           @Query(
            value = "SELECT * FROM user where user_id = ?", 
           nativeQuery = true)
           List notifchat(Long param);

           @Query(
            value = "SELECT * FROM msm", 
           nativeQuery = true)
           List notifmembersm();

           @Query(
            value = "SELECT * FROM user where user_id =?", 
           nativeQuery = true)
           List notifnilai(Long param);

        //dapatkan notif untuk admin umum
        @Query(
        value = "select notif_id,notif_flag,notif_isi,date_format(str_to_date(notif_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),n.user_id from notif n, user u, users_roles ur, role r where n.user_id = u.user_id and u.user_id = ur.user_id and ur.role_id = r.id and r.name = 'Admin Umum' order by notif_id desc", 
        nativeQuery = true)
        List selectindex();


         //dapatkan notif untuk admin event
         @Query(
        value = "select notif_id,notif_flag,notif_isi,date_format(str_to_date(notif_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),n.user_id from notif n, user u, users_roles ur, role r where n.user_id = u.user_id and u.user_id = ur.user_id and ur.role_id = r.id and r.name = 'Admin Event' order by notif_id desc", 
        nativeQuery = true)
        List selectindexevent();


        //dapatkan notif untuk admin umum
        @Query(
        value = "select notif_id,notif_flag,notif_isi,date_format(str_to_date(notif_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),n.user_id from notif n, user u, users_roles ur, role r where n.user_id = u.user_id and u.user_id = ur.user_id and ur.role_id = r.id and r.name = 'Admin Sekolah Minggu' order by notif_id desc", 
        nativeQuery = true)
        List selectindexsm();


        //dapatkan notif untuk admin market
        @Query(
        value = "select notif_id,notif_flag,notif_isi,date_format(str_to_date(notif_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),n.user_id from notif n, user u, users_roles ur, role r where n.user_id = u.user_id and u.user_id = ur.user_id and ur.role_id = r.id and r.name = 'Admin Market' order by notif_id desc", 
        nativeQuery = true)
        List selectindexmarket();



        @Query(
            value = "select user_id from user where email =?", 
            nativeQuery = true)
            Integer selectusernotif(String param);
}