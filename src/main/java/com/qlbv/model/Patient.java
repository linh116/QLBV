package com.qlbv.model;

import java.io.Serializable;

public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long patientId;
	private String patientName;
	private String address;
	private String phone;
	private Long birthday;
	private String job;
	private String nation;
	private Long createDtm;
	private Long updateDtm;
	private boolean gender;
	public Patient() {
	}

	@Override
	public String toString() {
		return "Patient{" +
				"patientId=" + patientId +
				", patientName='" + patientName + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", birthday=" + birthday +
				", job='" + job + '\'' +
				", nation='" + nation + '\'' +
				", createDtm=" + createDtm +
				", updateDtm=" + updateDtm +
				", gender=" + gender +
				'}';
	}

	public Patient(Long patientId, String patientName, String address, String phone, Long birthday,
				   Long createDtm, Long updateDtm, boolean gender,
				   String job, String nation) {
		this.patientId = patientId;
		this.patientName = patientName;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.birthday = birthday;
		this.createDtm = createDtm;
		this.updateDtm = updateDtm;
		this.job = job;
		this.nation = nation;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
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
