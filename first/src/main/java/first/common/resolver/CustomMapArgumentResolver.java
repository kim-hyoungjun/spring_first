package first.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import first.common.common.CommandMap;

public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver{
	@Override
	public boolean supportsParameter(MethodParameter para) {
		return CommandMap.class.isAssignableFrom(para.getParameterType());
	}
	
	@Override
	public Object resolveArgument(MethodParameter para, ModelAndViewContainer mavContainer, NativeWebRequest webReq, WebDataBinderFactory binderFactory) throws Exception {
		CommandMap commandMap = new CommandMap();
		
		HttpServletRequest req = (HttpServletRequest)webReq.getNativeRequest();
		Enumeration<?> enumeration = req.getParameterNames();
		
		String key = null;
		String[] values = null;
		while(enumeration.hasMoreElements()) {
			key = (String)enumeration.nextElement();
			values = req.getParameterValues(key);
			if(values != null) {
				commandMap.put(key,  (values.length > 1) ? values:values[0]);
			}
		}
		return commandMap;
	}
}
