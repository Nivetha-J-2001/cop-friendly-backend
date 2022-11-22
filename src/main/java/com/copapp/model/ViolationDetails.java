package com.copapp.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="violationDetails")
public class ViolationDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long violationId;
	private String name;
	private String licenceNo;
	private String violationType;
	private String vehicleType;
	private String location;
	private String mailId;
	private String mobileNumber;
	private String date;
	private String time;
	private Long fineAmount;
	private String paymentType;
	private String paymentStatus;
	@Column(length = 750)	
	private String messageSend;
	
	@CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

	public ViolationDetails() {
		super();
	}

	public ViolationDetails(long violationId, String name, String licenceNo, String violationType, String vehicleType,
			String location, String mailId, String mobileNumber, String date, String time, Long fineAmount,
			String paymentType, String paymentStatus) {
		super();
		this.violationId = violationId;
		this.name = name;
		this.licenceNo = licenceNo;
		this.violationType = violationType;
		this.vehicleType = vehicleType;
		this.location = location;
		this.mailId = mailId;
		this.mobileNumber = mobileNumber;
		this.date = date;
		this.time = time;
		this.fineAmount = fineAmount;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
	}

	public long getViolationId() {
		return violationId;
	}

	public void setViolationId(long violationId) {
		this.violationId = violationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getViolationType() {
		return violationType;
	}

	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public Long getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Long fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

    public String getMessageSend() {
		return messageSend;
	}

	public void setMessageSend(String messageSend) {
		this.messageSend = messageSend;
	}
   

}
