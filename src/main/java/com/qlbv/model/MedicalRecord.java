package com.qlbv.model;

import java.io.Serializable;

public class MedicalRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long recordId;
	private Long patientId;
	private Long doctorId;
	private String reason;
	private String symptom;
	private String guess;
	private String note;
	private Long createdDtm;
	public MedicalRecord() {
	}

	public MedicalRecord(Long recordId, Long patientId, Long doctorId, String reason, String symptom, String guess, String note, Long createdDtm) {
		this.recordId = recordId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.reason = reason;
		this.symptom = symptom;
		this.guess = guess;
		this.note = note;
		this.createdDtm = createdDtm;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getCreatedDtm() {
		return createdDtm;
	}

	public void setCreatedDtm(Long createdDtm) {
		this.createdDtm = createdDtm;
	}
}
