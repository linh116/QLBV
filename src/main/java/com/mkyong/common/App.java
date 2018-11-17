package com.mkyong.common;

import com.mkyong.handler.BaseHandler;
import com.mkyong.handler.filter.ParameterFilter;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mkyong.stock.bo.StockBo;
import com.mkyong.stock.model.Stock;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App 
{
    public static void main( String[] args ) throws IOException {


		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		HttpContext context = server.createContext("/test", new BaseHandler());
		context.getFilters().add(new ParameterFilter());
		server.setExecutor(null); // creates a default executor
		server.start();


    	ApplicationContext appContext = 
    		new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	
    	StockBo stockBo = (StockBo)appContext.getBean("stockBo");
    	/** insert **/
    	Stock stock = new Stock();
    	stock.setStockCode("7668");
    	stock.setStockName("HAIO");
    	stockBo.save(stock);
    	
    	/** select **/
    	Stock stock2 = stockBo.findByStockCode("7668");
    	System.out.println(stock2);
    	
    	/** update **/
    	stock2.setStockName("HAIO-1");
    	stockBo.update(stock2);
    	
    	/** delete **/
    	stockBo.delete(stock2);
    	
    	System.out.println("Done");
    }
}
