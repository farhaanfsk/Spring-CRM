package com.fsk.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class loggingAspect {
	private static final Logger LOGGER = LogManager.getLogger(loggingAspect.class);

	@Pointcut("execution(* com.fsk.spring.controller.*.*(..))")
	public void controllerAllMethods() {

	}

	@Pointcut("execution(* com.fsk.spring.dao.*.*(..))")
	public void daoAllMethods() {

	}

	@Pointcut("execution(* com.fsk.spring.service.*.*(..))")
	public void serviceAllMethods() {

	}

	@Pointcut("execution(* com.fsk.spring.entity.*.*(..))")
	public void entity() {

	}

	@Before("controllerAllMethods() || serviceAllMethods()")
	public void beforeController(JoinPoint jp) {
		LOGGER.info("===>Before method {}", jp.getSignature().toShortString());
		for (Object arg : jp.getArgs())
			LOGGER.info("Before method arguments {}", arg);
	}

	@AfterReturning(pointcut = "controllerAllMethods() || serviceAllMethods()", returning = "result")
	public void AfterReturningcontroller(JoinPoint jp, String result) {
		LOGGER.info("===>After returning method {}", jp.getSignature().toShortString());
		LOGGER.info("returned value {}", result);
	}

	@AfterThrowing(pointcut = "controllerAllMethods() || daoAllMethods() || serviceAllMethods()", throwing = "ex")
	public void AfterThrowing(JoinPoint jp, Throwable ex) {
		LOGGER.info("===>After Throwing exception method {}", jp.getSignature().toShortString());
		LOGGER.error("exception thrown is {}", ex.getMessage());
	}

	@After("entity()")
	public void after(JoinPoint jp) {
		LOGGER.info("===>After method {}", jp.getSignature().toShortString());
	}

	@Around("daoAllMethods()")
	public Object aroundDAO(ProceedingJoinPoint jp) throws Throwable {
		LOGGER.info("===>Around method{}", jp.getSignature().toShortString());
		long start = System.currentTimeMillis();
		LOGGER.info("===>Before proceeding Time = {}", start);
		Object object = jp.proceed();
		long end = System.currentTimeMillis();
		LOGGER.info("===>After proceeding Time = {}", end);
		LOGGER.info("===>Execution Time = {}", end - start);
		return object;
	}
}
