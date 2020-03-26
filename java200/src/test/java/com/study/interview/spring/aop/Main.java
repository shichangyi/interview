package com.study.interview.spring.aop;



import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
		CrmIdService service = context.getBean(CrmIdService.class);
		List<CrmIdEntity> list = service.query(21266L);
		System.out.println(list);
	}
}
