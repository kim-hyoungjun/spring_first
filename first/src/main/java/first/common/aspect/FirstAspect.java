package first.common.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class FirstAspect {
	protected Log logger = LogFactory.getLog(FirstAspect.class);
	
	// 공통으로 사용할 포인트 컷을 지정
	// first 패키지 안의 Controller로 끝나는 클래스의 모든 메소드에 적용
	@Pointcut("execution(* first..controller.*Controller.*(..))")
	public void commonPointCut() {}
	
	// Before Advice
	// 위에서 정의한 공통 포인트 컷을 사용
	@Before("commonPointCut()")
	public void beforeMethod(JoinPoint jp) throws Exception {
		logger.info("[FirstAspect] : beforeMethod() called ....");
		
		// 호출될 메소드의 인자
		Object arg[] = jp.getArgs();
		
		// 인자의 갯수 출력
		logger.info("[FirstAspect] : args length : " + arg.length);
		
		// 첫 번째 인자의 클래스 명 출력
		logger.info("[FirstAspect] : arg0 name : " + arg[0].getClass().getName());
				
		// 호출 될 메소드 명 출력
		logger.info("[FirstAspect] : Method name : " + jp.getSignature().getName());
	}
	
	// After Advice 입니다.
	@After("commonPointCut()")
	public void afterMethod(JoinPoint jp) throws Exception {
		logger.info("[First Aspect] : afterMetod() called ....");
	}
	
	// After Returning Advice 
	// 이 Advice는 반환 값을 갖을 수 있다.
	@AfterReturning(pointcut="commonPointCut()", returning="returnString")
	public void afterReturningMethod(JoinPoint jp, String returnString) throws Exception {
		logger.info("[First Aspect] : afterReturningMetod() called ....");
		//  호출된 메서드의 반환값 출력
		logger.info("[First Aspect] : afterReturningMetod() returnString : " + returnString);
	}
	
	// Around Advice	
	// 포인트 컷을 직접 지정
	@Around("execution(* first..controller.*Controller.*(..))")
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("[First Aspect] : aroundMetod() before called ....");
		Object result = pjp.proceed();
		logger.info("[First Aspect] : aroundMetod() after called ....");
		
		return result;
	}
	
	// 예외상황 Advice
	@AfterThrowing(pointcut="commonPointCut()", throwing="exception")
	public void afterThrowingMethod(JoinPoint jp, Exception exception) throws Exception {
		logger.info("[First Aspect] : afterThrowingMethod() called ....");
		
		// 에외 발생한 메서드 출력
		logger.info("[First Aspect] : exception method : + " + exception.getMessage());
	}
}
