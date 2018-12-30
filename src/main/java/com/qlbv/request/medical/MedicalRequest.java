package com.qlbv.request.medical;

import com.google.gson.Gson;
import com.qlbv.bo.BoManager;
import com.qlbv.common.Helper;
import com.qlbv.common.Logger;
import com.qlbv.framework.SessionManager;
import com.qlbv.model.MedicalRecord;
import com.qlbv.model.Patient;
import com.qlbv.model.RequestPatient;
import com.qlbv.model.User;
import com.qlbv.model.view.RequestPatientView;
import com.qlbv.request.BaseRequest;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MedicalRequest extends BaseRequest {
    static Logger _logger = new Logger();
    public MedicalRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        super(httpExchange, templateFilePath);
    }

    protected int doGet() {
        putToView("isMedical", true);
        putToView("isIndex", true);
        return 200;
    }

    protected int doPost() {

        String action = Helper.getString(params, "action");
        if ("save_diagnose".equals(action)){
            Long requestId = Helper.getLong(params, "requestId");
            String guess = Helper.getString(params, "guess");
            String note = Helper.getString(params, "note");
            Long patientId = Helper.getLong(params, "patientId");
            String reason = Helper.getString(params, "reason");
            String symptom = Helper.getString(params, "symptom");

            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setCreatedDtm(System.currentTimeMillis());
            medicalRecord.setGuess(guess);
            medicalRecord.setNote(note);
            medicalRecord.setPatientId(patientId);
            medicalRecord.setReason(reason);
            medicalRecord.setSymptom(symptom);
            medicalRecord.setDoctorId(user.getUserId());

            // save data benh nhan
            BoManager.medicalRecordBo.save(medicalRecord);

            //update status request -> done
            BoManager.requestPatientBo.updateStatusRequest(requestId, RequestPatient.REQUEST_STATUS.DONE.name());
        }else if ("fetch_request_patient".equals(action)){
            //get list patient waiting after update
            List<RequestPatient> requestToday = BoManager.requestPatientBo.findAllRequestToday();
            if (CollectionUtils.isNotEmpty(requestToday)){
                Map<Long, Long> mapPatientIdToRequestId = requestToday.stream().collect(Collectors.toMap(t -> t.getPatientId(), t -> t.getRequestId()));
                List<Long> listId = requestToday.stream().map(t -> t.getPatientId()).collect(Collectors.toList());
                List<Patient> listPatient = BoManager.patientBo.getListPatient(listId);
                List<RequestPatientView> listPatientSorted = new ArrayList<>();
                for(Long pid : mapPatientIdToRequestId.keySet()){
                    for (Patient patient : listPatient){
                        if (patient.getPatientId().equals(pid)){
                            listPatientSorted.add(new RequestPatientView(mapPatientIdToRequestId.get(pid), patient));
                        }
                    }
                }
                putToView("listRequest", new Gson().toJson(listPatientSorted));
            }
        }
        return 200;
    }
}
