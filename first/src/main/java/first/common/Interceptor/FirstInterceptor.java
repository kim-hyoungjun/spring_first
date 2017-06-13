package first.common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FirstInterceptor extends HandlerInterceptorAdapter{
	protected Log logger = LogFactory.getLog(FirstInterceptor.class);
	
	// Controller 직전에 수행
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		logger.info("[FirstInterceptor] PreHandle Call ....");
		if (handler instanceof HandlerMethod) {
			// HandlerMethod는 뒤에 실행되는 컨트롤러 Method			
			// Method, Instance, type, Annotation을 확인 할 수 있음
			HandlerMethod method = (HandlerMethod) handler;			
			logger.info("[FirstInterceptor] handler URL : " + req.getRequestURI());
			logger.info("[FirstInterceptor] handler method name : " + method.getMethod().getName());
			logger.info("[FirstInterceptor] handler Class name : " + method.getClass().getName());			
		}
		
		return true;
	}
	
	// Controller 직후에 수행
	public void postHandler (HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("[FirstInterceptor] postHandle Call ....");
	}
	
	// View 렌더링이 끝난 직후에 수행
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {
		logger.info("[FirstInterceptor] afterCompletion Call ....");
	}
	
	// 비동기 호출 시 수행
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		logger.info("[FirstInterceptor] afterConcurrentHandlingStarted Call ....");
	}
}
