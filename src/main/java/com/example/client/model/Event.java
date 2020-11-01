package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long event_id;
	private String event_judul;
	private String event_keterangan;
	private String event_tanggal;
	private String event_waktu;
	private String event_foto;
	
	
	
	public Event() {
	}

	protected Event(String event_judul,String event_keterangan,String event_tanggal,String event_waktu,String event_foto) {
		super();
		this.event_judul = event_judul;
		this.event_keterangan = event_keterangan;
		this.event_tanggal = event_tanggal;
		this.event_waktu = event_waktu;
		this.event_foto= event_foto;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}

	public String getEvent_judul() {
		return event_judul;
	}

	public void setEvent_judul(String event_judul) {
		this.event_judul = event_judul;
    }
    

    public String getEvent_keterangan() {
		return event_keterangan;
	}

	public void setEvent_keterangan(String event_keterangan) {
		this.event_keterangan = event_keterangan;
    }
    


    public String getEvent_tanggal() {
		return event_tanggal;
	}

	public void setEvent_tanggal(String event_tanggal) {
		this.event_tanggal = event_tanggal;
    }
    


    public String getEvent_waktu() {
		return event_waktu;
	}

	public void setEvent_waktu(String event_waktu) {
		this.event_waktu = event_waktu;
	}

	public String getEvent_foto() {
		return event_foto;
	}

	public void setEvent_foto(String event_foto) {
		this.event_foto = event_foto;
	}
}