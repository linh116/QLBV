package com.qlbv.common;

import com.qlbv.handler.*;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.rythmengine.Rythm;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class App
{
	static final Logger LOGGER = new Logger();
    public static void main( String[] args ) throws IOException {
    	LOGGER.info("File Encoding = " + System.getProperty("file.encoding"));

    	//init rythmengine
		initRythm();

		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		HttpContext context = server.createContext("/", new BaseHandler());
		server.createContext("/resources", new StaticHandler(true, false));
		server.createContext("/login", new LoginHandler());
		server.createContext("/medical", new MedicalHandler());
		server.createContext("/common", new CommonHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		LOGGER.info("start server success");

    }

    private static void initRythm(){
    	LOGGER.info(System.getProperty("user.dir"));
		String root = System.getProperty("user.dir") + "\\resources\\rythm";
		LOGGER.info("Home template rythm: " + root);
		// use Map to store the configuration
		Map<String, Object> map = new HashMap<String, Object>();
		// tell rythm where to find the template files
		map.put("home.template", root);
		// init Rythm with our predefined configuration
		Rythm.init(map);
	}
}
