package com.qlbv.request.medical;

import com.google.gson.Gson;
import com.qlbv.bo.BoManager;
import com.qlbv.common.Helper;
import com.qlbv.common.Logger;
import com.qlbv.model.MedicalRecord;
import com.qlbv.model.Patient;
import com.qlbv.model.RequestPatient;
import com.qlbv.model.view.RequestPatientView;
import com.qlbv.request.BaseRequest;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.qlbv.bo.BoManager.*;

public class MedicalRequest extends BaseRequest {
    static Logger _logger = new Logger();
    public MedicalRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        super(httpExchange, templateFilePath);
    }

    protected int doGet() {
        putToView("isMedical", true);
        putToView("isIndexTab", true);
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
            int fee = Helper.getInt(params, "fee");
            String prescription = Helper.getString(params, "prescription");

            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setCreatedDtm(System.currentTimeMillis());
            medicalRecord.setGuess(guess);
            medicalRecord.setNote(note);
            medicalRecord.setPatientId(patientId);
            medicalRecord.setReason(reason);
            medicalRecord.setSymptom(symptom);
            medicalRecord.setDoctorId(user.getUserId());
            medicalRecord.setFee(fee);
            medicalRecord.setPresciptionCode(prescription);
            // save data benh nhan
            medicalRecordBo.save(medicalRecord);

            //update status request -> done
            requestPatientBo.updateStatusRequest(requestId, RequestPatient.REQUEST_STATUS.DONE.name());
        }else if ("fetch_request_patient".equals(action)){
            //get list patient waiting after update
            List<RequestPatient> requestToday = requestPatientBo.findAllRequestToday();
            if (CollectionUtils.isNotEmpty(requestToday)){
                Map<Long, Long> mapPatientIdToRequestId = requestToday.stream().collect(Collectors.toMap(t -> t.getPatientId(), t -> t.getRequestId()));
                List<Long> listId = requestToday.stream().map(t -> t.getPatientId()).collect(Collectors.toList());
                List<Patient> listPatient = patientBo.getListPatient(listId);
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
        }else if ("fetch_history_patient".equals(action)){
            Long patientId = Helper.getLong(params, "patientId");
            if (patientId <= 0){
                return 400;
            }

            //get history of patient
            List<MedicalRecord> listMedicalRecord = medicalRecordBo.getListMedicalRecordOfPatient(patientId);
            putToView("listMedicalRecord", new Gson().toJson(listMedicalRecord));

        }
        return 200;
    }
}
