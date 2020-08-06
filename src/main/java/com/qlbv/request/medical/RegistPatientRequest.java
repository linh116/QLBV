package com.qlbv.request.medical;

import com.google.gson.Gson;
import com.qlbv.bo.BoManager;
import com.qlbv.bo.result.BooleanResult;
import com.qlbv.common.Constant;
import com.qlbv.common.Helper;
import com.qlbv.model.Patient;
import com.qlbv.model.RequestPatient;
import com.qlbv.request.BaseRequest;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.collections.CollectionUtils;
import org.rythmengine.logger.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RegistPatientRequest extends BaseRequest {
    public RegistPatientRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        super(httpExchange, templateFilePath);
    }

    protected int doGet() {
        putToView("isMedical", true);
        putToView("isRegist", true);
        return 200;
    }

    protected int doPost() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Long patientId = Helper.getLong(params, "patientId");
        String patientName = Helper.getString(params, "patientName");
        String patientAddress = Helper.getString(params, "patientAddress");
        String patientPhone = Helper.getString(params, "patientPhone");
        String patientBD = Helper.getString(params, "patientBD");
        Boolean patientGender = Helper.getBoolean(params, "patientGender");
        String patientJob = Helper.getString(params, "patientJob");
        String patientNation = Helper.getString(params, "patientNation");
        long birthdayLong = 0;
        try {
            Date birthDay = sdf.parse(patientBD);
            birthdayLong = birthDay.getTime();
        } catch (ParseException e) {
            birthdayLong = 0;
        }

        //truong hop benh nhan chua co trong database
        if (patientId == null || patientId == 0){
            //dang ky benh nhan moi
            Patient patient = new Patient();
            patient.setAddress(patientAddress);
            patient.setBirthday(birthdayLong);
            patient.setCreateDtm(System.currentTimeMillis());
            patient.setPatientName(patientName);
            patient.setPhone(patientPhone);
            patient.setUpdateDtm(System.currentTimeMillis());
            patient.setGender(patientGender);
            patient.setJob(patientJob);
            patient.setNation(patientNation);
            BoManager.patientBo.save(patient);
            patientId = patient.getPatientId();
            Logger.info("New Patient: %s", patient);
        }

        //dang ky benh nhan kham trong hom nay:
        if (patientId == null || patientId == 0){
            return 500;
        }
        RequestPatient request = new RequestPatient();
        request.setPatientId(patientId);
        request.setStatus(RequestPatient.REQUEST_STATUS.WAITING.name());
        request.setCreateDtm(System.currentTimeMillis());
        BooleanResult result = BoManager.requestPatientBo.save(request);
        if (result.isSuccess() == false){
            putErrorToView(result.getErrorMsg());
            return 412;
        }

        return 200;
    }
}
