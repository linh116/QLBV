package com.qlbv.bo;

import com.qlbv.dao.PatientDao;
import com.qlbv.model.Patient;

import java.util.List;

public class PatientBo {
	
	PatientDao patientDao;
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	public void save(Patient patient){
		patientDao.save(patient);
	}
	
	public void update(Patient patient){
		patientDao.update(patient);
	}
	
	public void delete(Patient patient){
		patientDao.delete(patient);
	}

	public List<Patient> getListPatient(List<Long> listId) {
		return patientDao.getListPatient(listId);
	}

	public List<Patient> getListPatient(String name, Long birtday){
		return patientDao.getListPatient(name, birtday);
	}
}
