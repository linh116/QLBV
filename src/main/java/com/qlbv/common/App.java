package com.qlbv.common;

import com.qlbv.bo.BoManager;
import com.qlbv.bo.PatientBo;
import com.qlbv.bo.RequestPatientBo;
import com.qlbv.bo.UserBo;
import com.qlbv.handler.*;
import com.qlbv.model.Patient;
import com.qlbv.model.RequestPatient;
import com.qlbv.model.User;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		HttpContext context = server.createContext("/", new BaseHandler());
		server.createContext("/resources", new StaticHandler(true, false));
		server.createContext("/login", new LoginHandler());
		server.createContext("/medical", new MedicalHandler());
		server.createContext("/common", new CommonHandler());
		server.setExecutor(null); // creates a default executor
		server.start();

		/*Patient patent= new Patient();
		patent.setUpdateDtm(System.currentTimeMillis());
		patent.setPhone("0972708787");
		patent.setPatientName("Linhnd");
		patent.setCreateDtm(System.currentTimeMillis());
		patent.setBirthday(System.currentTimeMillis());
		patent.setAddress("daklak");
		BoManager.patientBo.save(patent);*/
    }
}
