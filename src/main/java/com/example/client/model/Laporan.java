package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laporan {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long laporan_id;
    private String startdate;
    private String enddate;
    
    
    public Laporan() {
	}

	protected Laporan(String startdate,String enddate) {
		super();
		this.startdate = startdate;
		this.enddate = enddate;
	}
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getLaporan_id() {
		return laporan_id;
	}

	public void setLaporan_id(Long laporan_id) {
		this.laporan_id = laporan_id;
    }


    public String getStartdate(){
		return startdate;
    }

	public void setStartdate(String startdate) {
		this.startdate = startdate;
    }

	public String getEnddate(){
		return enddate;
	}
    
    public void setEnddate(String enddate) {
		this.enddate = enddate;
    }

	

}