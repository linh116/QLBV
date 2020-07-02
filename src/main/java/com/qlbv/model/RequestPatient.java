package com.qlbv.model;

import java.io.Serializable;

public class RequestPatient implements Serializable {

	private static final long serialVersionUID = 1L;
	public enum REQUEST_STATUS{DEFAULT, WAITING, DONE}

    private Long requestId;
	private Long patientId;
	private String status;
	private Long createDtm;

	public RequestPatient(Long requestId, Long patientId, String status, Long createDtm) {
		this.requestId = requestId;
		this.patientId = patientId;
		this.status = status;
		this.createDtm = createDtm;
	}

	public RequestPatient() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(Long createDtm) {
		this.createDtm = createDtm;
	}
}
