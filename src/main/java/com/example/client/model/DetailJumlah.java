package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DetailJumlah {
    public int jumlah;
    

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
    }

	public int getJumlah()
	{
		return this.jumlah;
	}

}