package com.study.interview.spring.aop;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

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

import com.alibaba.fastjson.JSONObject;

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
		System.out.println("before, f_corp_id = " + f_corp_id);
		if(1>0) {
			throw new RuntimeException("中断执行");
		}
	}

	@After(value = "query()")
	public void after(JoinPoint point) {
		System.out.println("after ");
	}

	@AfterReturning(pointcut = "query()", returning = "res")
	public void afterReturning(JoinPoint point, Object res) {
		String format = "afterReturning , args = %s, res = %s";
		Object[] args = point.getArgs();// 获取调用参数
		System.out.println(String.format(format, JSONObject.toJSONString(args), res));
	}

	@AfterThrowing(pointcut = "query()", throwing = "e")
	public void afterThrowing(JoinPoint point, Throwable e) {
		String format = "afterThrowing , args = %s";
		Object[] args = point.getArgs();// 获取调用参数
		System.out.println(String.format(format, args));
		System.out.println(e.getMessage());
		
		
		try {
			// @Around begin 执行区域
			// @Before 执行区域
			
			// 处理正在的业务
			// Object res = point.proceed();
			
			// @Around end 执行区域
			// @AfterReturning 执行区域
		}catch (Exception ex) {
			// @@AfterThrowing 执行区域
		}finally {
			// @After 执行区域
		}
	}

	//
	@Around(value = "query()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();// 获取调用参数
		System.out.println("around:begin");
		Long f_corp_id = (Long) args[0];
		// 1. 设置默认参数
		if (f_corp_id == null) {
			f_corp_id = 0L;
		}
		// 2. 21291L 企业无权限查看数据
		if (f_corp_id.equals(21291L)) {
			return new ArrayList<>();
		}
		// 3. 执行业务，统计执行时间
		long t1 = System.currentTimeMillis();
		Object res = point.proceed();
		long t2 = System.currentTimeMillis();
		System.out.println(String.format("around:end , 执行 %s ， 参数 = %s , 耗时 = %s ms", point.getSignature().getName(),
				JSONObject.toJSONString(args), t2 - t1));
		return res;

	}

}
