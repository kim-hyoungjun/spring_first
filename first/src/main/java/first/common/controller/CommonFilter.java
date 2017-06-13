package first.common.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonFilter {
	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/views/filter.do", method = RequestMethod.GET)
	public String filter(Model model) {
		logger.info("call filter.do");
		
		return "views/filter";
	}
}
