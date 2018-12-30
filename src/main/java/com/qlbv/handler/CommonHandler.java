package com.qlbv.handler;

import com.qlbv.common.Helper;
import com.qlbv.request.common.FindPatientRequest;
import com.qlbv.request.medical.MedicalRequest;
import com.qlbv.request.medical.RegistPatientRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class CommonHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String action = Helper.getAction(httpExchange);
        switch (action.toLowerCase()){
            case "findpatient":
                new FindPatientRequest(httpExchange, "");
                break;
        }
    }
}
