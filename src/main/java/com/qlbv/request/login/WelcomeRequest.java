package com.qlbv.request.login;

import com.qlbv.request.BaseRequest;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class WelcomeRequest extends BaseRequest {

    public WelcomeRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        super(httpExchange, templateFilePath);
    }

    protected int doGet() {

        putToView("isIndex", true);
        return 200;
    }

    protected int doPost() {
        return 200;
    }
}
