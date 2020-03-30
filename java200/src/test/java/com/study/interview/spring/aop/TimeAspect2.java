package com.study.interview.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect2 {

	@Pointcut("execution(* com.study.interview.spring.aop.CrmIdServiceImpl.query(..))")
	public void query() {
	}

	@Before("query()")
	public void before(JoinPoint point) {
		// do xxx
	}

	@After(value = "query()")
	public void after(JoinPoint point) {
		// do xxx
	}

	@AfterReturning(pointcut = "query()", returning = "res")
	public void afterReturning(JoinPoint point, Object res) {
		// do xxx
	}

	@AfterThrowing(pointcut = "query()", throwing = "e")
	public void afterThrowing(JoinPoint point, Throwable e) {
		// do xxx
	}

	//
	@Around(value = "query()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object res = point.proceed();
		return res;

	}

}
