package com.qlbv.model.view;

import com.qlbv.model.Patient;

public class RequestPatientView extends Patient{
	private Long requestId;

	public RequestPatientView(Long requestId, Patient patient) {
		this.requestId = requestId;
		setPatientId(patient.getPatientId());
		setPatientName(patient.getPatientName());
		setAddress(patient.getAddress());
		setBirthday(patient.getBirthday());
		setCreateDtm(patient.getCreateDtm());
		setGender(patient.getGender());
		setPhone(patient.getPhone());
		setUpdateDtm(patient.getUpdateDtm());
		setNation(patient.getNation());
		setJob(patient.getJob());
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
}