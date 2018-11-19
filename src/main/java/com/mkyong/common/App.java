package com.mkyong.common;

import com.mkyong.bo.StockBo;
import com.mkyong.handler.BaseHandler;
import com.mkyong.handler.LoginHandler;
import com.mkyong.handler.StaticHandler;
import com.mkyong.handler.filter.ParameterFilter;
import com.mkyong.model.Stock;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws IOException {


		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		HttpContext context = server.createContext("/", new BaseHandler());
		server.createContext("/resources", new StaticHandler(true, false));
		server.createContext("/login", new LoginHandler());
		context.getFilters().add(new ParameterFilter());
		server.setExecutor(null); // creates a default executor
		server.start();


    	ApplicationContext appContext =
    		new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

    	StockBo stockBo = (StockBo)appContext.getBean("stockBo");
//    	* insert *
    	Stock stock = new Stock();
    	stock.setStockCode("7668");
    	stock.setStockName("HAIO");
    	stockBo.save(stock);

//    	* select *
    	Stock stock2 = stockBo.findByStockCode("7668");
    	System.out.println(stock2);

//    	* update *
    	stock2.setStockName("HAIO-1");
    	stockBo.update(stock2);

//    	* delete *
    	stockBo.delete(stock2);

    	System.out.println("Done");
    }
}
