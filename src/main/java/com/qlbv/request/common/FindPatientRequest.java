package com.qlbv.request.common;

import com.google.gson.Gson;
import com.qlbv.bo.BoManager;
import com.qlbv.common.Helper;
import com.qlbv.common.Logger;
import com.qlbv.model.Patient;
import com.qlbv.model.RequestPatient;
import com.qlbv.request.BaseRequest;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FindPatientRequest extends BaseRequest {
    static Logger _logger = new Logger();
    public FindPatientRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        super(httpExchange, templateFilePath);
    }

    protected int doGet() {
        return 200;
    }

    protected int doPost() {
        String patientName = Helper.getString(params,"patientName");
        Long birthday = Helper.getLong(params,"birthday");

        List<Patient> listPatient = BoManager.patientBo.getListPatient(patientName, birthday);
        putToView("listPatient", new Gson().toJson(listPatient));

        return 200;
    }
}
