package com.health.mediwiseai.model;

import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class HealthReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] fileData; // or store in file system and save path

    private String diagnosis;
    private String medicine;
    private String precautions;
    private String cure;

    @ManyToOne
    private User user;

    private LocalDateTime uploadedAt = LocalDateTime.now();

	public HealthReport() {
		super();
	}

	public HealthReport(Long id, String fileName, byte[] fileData, String diagnosis, String medicine,
			String precautions, String cure, User user, LocalDateTime uploadedAt) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileData = fileData;
		this.diagnosis = diagnosis;
		this.medicine = medicine;
		this.precautions = precautions;
		this.cure = cure;
		this.user = user;
		this.uploadedAt = uploadedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getPrecautions() {
		return precautions;
	}

	public void setPrecautions(String precautions) {
		this.precautions = precautions;
	}

	public String getCure() {
		return cure;
	}

	public void setCure(String cure) {
		this.cure = cure;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	@Override
	public String toString() {
		return "HealthReport [id=" + id + ", fileName=" + fileName + ", fileData=" + Arrays.toString(fileData)
				+ ", diagnosis=" + diagnosis + ", medicine=" + medicine + ", precautions=" + precautions + ", cure="
				+ cure + ", user=" + user + ", uploadedAt=" + uploadedAt + "]";
	}
    
    
}
