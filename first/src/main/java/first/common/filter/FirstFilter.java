package first.common.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;

public class FirstFilter implements Filter{
	Logger logger = Logger.getLogger(this.getClass());
	private String encoding;
	
	/**
	 * init 함수는 컨텐스트 로드시 호출
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("init Call");
		
		// web.xml에서 필터 등록시 정의한 파라미터를 갖어온다.
		this.encoding = config.getInitParameter("encoding");
	}
	
	/**
	 * destroy는 컨텍스트가 종료될 때 호출됩니다.
	 */
	@Override
	public void destroy() {
		logger.info("destroy call");
		
	}
	/**
	 * Filter 실행 부분
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// Controller 처리 전에 처리를 수행
		// 파라미터 값으로 POST 데이터의 인코딩을 지정
		req.setCharacterEncoding(this.encoding);;
		
		// 요청과 응답에 필요한 처리를 수행할 Wrapper를 생성
		ServletRequest requestWrapper = new TestRequestWrapper((HttpServletRequest) req);
		ServletResponse responseWrapper = new TestResponseWrapper ((HttpServletResponse) res);
		logger.info("before doFilter");
		
		// 다음 필터의 호출, 실제 자원 처리
		chain.doFilter(requestWrapper, responseWrapper);
		
		// 응답에 대한 처리
		logger.info("after doFilter");
		
		// 응답 래퍼를 이용하여 출력문자를 대문자로 변경
		if(res.isCommitted() == false) {
			res.getWriter().write(responseWrapper.toString().toUpperCase());
		}		
	}
	/** 
	 * 요청 Wrapper
	 */
	class TestRequestWrapper extends HttpServletRequestWrapper {
		public TestRequestWrapper(HttpServletRequest req) {
			super(req);
		}
		
		// 입력 파라미터에서 <, >를 제거
		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if(value == null) value = "";			
			return value.replaceAll("[<>]",  "");
		}
	}
	
	/**
	 * 응답 Wrapper
	 */
	class TestResponseWrapper extends HttpServletResponseWrapper {
		protected CharArrayWriter charWriter;
		
		public TestResponseWrapper(HttpServletResponse res) {
			super(res);
			charWriter = new CharArrayWriter();
		}
		
		// 출력을 나중에 수정할 수 있도록 Buffering
		@Override
		public PrintWriter getWriter() throws IOException {
			return new PrintWriter(charWriter);
		}
	
		@Override
		public String toString() {
			return charWriter.toString();
		}
	}
}
