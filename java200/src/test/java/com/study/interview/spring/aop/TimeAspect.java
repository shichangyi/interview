package com.study.interview.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

	@Pointcut("execution(* com.study.interview.spring.aop.CrmIdServiceImpl.query(..))")
	public void query() {
	}

	@Before("query()")
	public void before(JoinPoint point) {
		Object obj = point.getTarget();// 获取目标对象
		Object[] args = point.getArgs();// 获取调用参数
		Long f_corp_id = (Long) args[0];
		System.out.println("f_corp_id = " + f_corp_id);
	}

	@After(value = "query()")
	public void after(JoinPoint point) {
		System.out.println("after ");
	}

	@AfterReturning(pointcut = "query()", returning = "res")
	public void afterReturning(JoinPoint point, Object res) {
		String format = "afterReturning , %s = {}, args = %s";
		Object[] args = point.getArgs();// 获取调用参数
		System.out.println(String.format(format, res, args));
	}
	
	@AfterThrowing(pointcut = "query()", throwing = "e")
	public void afterThrowing(JoinPoint point, Throwable e) {
		String format = "afterReturning , args = %s";
		Object[] args = point.getArgs();// 获取调用参数
		System.out.println(String.format(format, args));
		System.out.println(e.getMessage());
		
	}
	
	

}
