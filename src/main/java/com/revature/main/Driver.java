package com.revature.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.revature.config.HibernateConfig;
import com.revature.service.ManagerService;

public class Driver {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		
		ManagerService caveService = context.getBean("managerService", ManagerService.class);
		System.out.println(caveService.getById(1));
		
		context.close();
	}

}