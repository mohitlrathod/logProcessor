package com.mohit.model;

import java.time.LocalTime;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author MohitRathod
 *
 */

@Entity
public class LogProfile implements Comparable<LogProfile>{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long pid;
	
	private String id;
	private String state;	
	private String type;
	private String host;
	private String timestamp ;
	private long duration;
	private boolean alert;
	
	
	
	


	
	public LogProfile() {
		super();
		// TODO Auto-generated constructor stub
	}




	public LogProfile(String id, String state, String type, String host, String timestamp, long duration,
			boolean alert) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.host = host;
		this.timestamp = timestamp;
		this.duration = duration;
		this.alert = alert;
	}

	
	

	public Long getPid() {
		return pid;
	}




	public void setPid(Long pid) {
		this.pid = pid;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public boolean isAlert() {
		return alert;
	}




	public void setAlert(boolean alert) {
		this.alert = alert;
	}




	public long getDuration() {
		return duration;
	}


	public void setDuration(long duration) {
		this.duration = duration;
	}


	public String getiD() {
		return id;
	}
	public void setiD(String iD) {
		this.id = iD;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	




	@Override
	public String toString() {
		return "LogProfile [id=" + id + ", state=" + state + ", type=" + type + ", host=" + host + ", timestamp="
				+ timestamp + ", duration=" + duration + ", alert=" + alert + "]";
	}




	@Override
	public int compareTo(LogProfile o) {
	
		
		return this.getiD().compareTo(o.getiD());
	}
	
	
	
	
	
	
}


