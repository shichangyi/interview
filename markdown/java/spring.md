# Spring AOP


## 1.动态代理 vs 静态代理

### 2. jdk 动态代理 vs cglib 动态代理

### 3. spring aop 的五种通知的
* 通知 签名
    @Before("query()")
    @After(value = "query()")
    @AfterReturning(pointcut = "query()", returning = "res")
    @AfterThrowing(pointcut = "query()", throwing = "e")
    @Around(value = "query()")

**5 种通知执行的顺序如下比喻**
``` java
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

     
            
```
**各个通知的签名**
``` java
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
```

### 4. spring aop 的Pointcut 语言


### 5. spring aop 源码解读+实现方式

