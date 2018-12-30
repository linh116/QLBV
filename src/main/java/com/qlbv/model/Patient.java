package com.qlbv.model;

import java.io.Serializable;

public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long patientId;
	private String patientName;
	private String address;
	private String phone;
	private Long birthday;
	private Long createDtm;
	private Long updateDtm;
	private boolean gender;
	public Patient() {
	}

	public Patient(Long patientId, String patientName, String address, String phone, Long birthday, Long createDtm, Long updateDtm, boolean gender) {
		this.patientId = patientId;
		this.patientName = patientName;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.birthday = birthday;
		this.createDtm = createDtm;
		this.updateDtm = updateDtm;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public Long getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(Long createDtm) {
		this.createDtm = createDtm;
	}

	public Long getUpdateDtm() {
		return updateDtm;
	}

	public void setUpdateDtm(Long updateDtm) {
		this.updateDtm = updateDtm;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
