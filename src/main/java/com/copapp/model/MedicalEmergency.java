package com.copapp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="medicalEmergency")
public class MedicalEmergency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long medicalId;
	private long noOfAffected;
	private String status;
	private String name;
	private String phoneNumber;
	private String location;
	private String priority;
	@Column(length = 750)
	private String messageSend;
	@CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

	public long getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(long medicalId) {
		this.medicalId = medicalId;
	}

	public long getNoOfAffected() {
		return noOfAffected;
	}

	public void setNoOfAffected(long noOfAffected) {
		this.noOfAffected = noOfAffected;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMessageSend() {
		return messageSend;
	}

	public void setMessageSend(String messageSend) {
		this.messageSend = messageSend;
	}

	public MedicalEmergency() {
		super();
	}

	public MedicalEmergency(long medicalId, long noOfAffected, String status, String name, String phoneNumber,
			String location, String priority) {
		super();
		this.medicalId = medicalId;
		this.noOfAffected = noOfAffected;
		this.status = status;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.priority = priority;
	}
    
    

}
