package com.qlbv.bo;

import com.qlbv.dao.MedicalRecordDao;
import com.qlbv.model.MedicalRecord;

import java.util.List;

public class MedicalRecordBo {
	
	MedicalRecordDao medicalRecordDao;
	public void setMedicalRecordDao(MedicalRecordDao medicalRecordDao) {
		this.medicalRecordDao = medicalRecordDao;
	}

	public void save(MedicalRecord medicalRecord){
		medicalRecordDao.save(medicalRecord);
	}
	
	public void update(MedicalRecord medicalRecord){
		medicalRecordDao.update(medicalRecord);
	}
	
	public void delete(MedicalRecord medicalRecord){
		medicalRecordDao.delete(medicalRecord);
	}

	public List<MedicalRecord> getListMedicalRecord(List<Long> listId) {
		return medicalRecordDao.getListMedicalRecord(listId);
	}

	public List<MedicalRecord> getListMedicalRecordOfPatient(Long patientId){
        return medicalRecordDao.getListMedicalRecordOfPatient(patientId);
    }

}
