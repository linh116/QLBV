package com.qlbv.bo;

import com.qlbv.bo.result.BooleanResult;
import com.qlbv.dao.RequestPatientDao;
import com.qlbv.model.RequestPatient;

import java.util.List;
import java.util.Optional;

public class RequestPatientBo {
	
	RequestPatientDao requestPatientDao;
	public void setRequestPatientDao(RequestPatientDao requestPatientDao) {
		this.requestPatientDao = requestPatientDao;
	}

	public BooleanResult save(RequestPatient requestPatient){
		List<RequestPatient> listPatientToday = findAllRequestToday();
		if (listPatientToday != null){
			if (listPatientToday.stream()
					.filter(p -> p.getPatientId().equals(requestPatient.getPatientId()))
					.findAny().
					isPresent())
				return new BooleanResult(false, "Bệnh nhân này đã đăng ký khám trong hôm nay rồi.");
		}

		requestPatientDao.save(requestPatient);
		return new BooleanResult();
	}
	
	public void update(RequestPatient requestPatient){
		requestPatientDao.update(requestPatient);
	}
	
	public void delete(RequestPatient requestPatient){
		requestPatientDao.delete(requestPatient);
	}

	public List<RequestPatient> findAllRequestToday(){
		return requestPatientDao.findAllRequestToday();
	}

	public RequestPatient findRequestById(Long requestId){
		return requestPatientDao.findRequestById(requestId);
	}

	public void updateStatusRequest(Long requestId, String newStatus){
		RequestPatient currentRequest = findRequestById(requestId);
		if (currentRequest == null) return;
		currentRequest.setStatus(newStatus);
		update(currentRequest);
	}
}
