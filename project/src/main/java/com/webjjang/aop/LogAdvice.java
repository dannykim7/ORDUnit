package com.webjjang.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

// 자동 생성 어노테이션
@Component
// Aspect 로 동작(AOP 프로그램)
@Aspect
@Log4j
public class LogAdvice {
	
	// aop로 실행될 메소드 작성
	// 밑의 (..) 의 .. 은 매개변수
	@Around("execution(* com.webjjang.*.service.*.*(..))")
	public Object logTimeAndInfo(ProceedingJoinPoint pjp) throws Throwable{

		log.info("======실행 속도와 처리 정보를 출력하는 AOP====== ");
	// 실행 전 처리
	// 시작 시간 저장 / 실행 클래스 출력 / 넘어가는 데이터 출력 / 실행 결과 출력
	// 시작 시간 저장
		long start = System.nanoTime();
		
		// 로그로 실행 객체 이름 출력 -> service
		log.info(" 실행하는 객체 : " + pjp.getTarget());
		log.info(" 실행 메소드 이름 : " + pjp.getSignature().getName() + "()");
		// 로그로 넘어가는 데이터 출력 하고싶지만 유닛 데이터의
		// 스킬명이 너무 길어서 각주 처리
		// log.info("* 넘기는 데이터 : " + Arrays.toString(pjp.getArgs()));
		
		
		// 실질적인 처리와 결과를 저장
		Object result = pjp.proceed();
		
		// 실행 후 처리
		// 처리 결과 데이터 출력 하고싶지만 유닛 데이터의
		// 스킬명이 너무 길어서 각주 처리
		// log.info("* 처리된 결과 : " + result);
		// 끝 시간 저장 / 처리 소요 출력
		long end = System.nanoTime();
		
		log.info("* 처리 소요 시간 : " + (end - start) + " ns ");
		
	return pjp.proceed();
	}

}
