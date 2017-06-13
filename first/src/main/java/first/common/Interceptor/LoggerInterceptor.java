package first.common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import first.common.Interceptor.LoggerInterceptor;

public class LoggerInterceptor extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("======================================          START         ======================================");
			// log.debug("Request URI \t: " + req.getRequestURI());
			log.debug("Request URL \t: " + req.getRequestURL());
		}
		
		return super.preHandle(req, res, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("======================================           END          ======================================");
		}		
	}
}
