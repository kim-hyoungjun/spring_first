package first.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import first.common.common.CommandMap;
import first.sample.service.SampleService;

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
								 
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value="/board/openBoardList.do")
    public ModelAndView openBoardList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardList");
		
		Map<String,Object> resultMap = sampleService.selectBoardPageList(commandMap.getMap());

		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("list", resultMap.get("result"));
		
		return mv;
		
		/* Paging 하기 이전 버전
    	ModelAndView mv = new ModelAndView("/board/boardList");
    	
    	List<Map<String,Object>> list = sampleService.selectBoardList(commandMap.getMap());
    	mv.addObject("list", list);
    	
    	return mv;
    	*/
    }

	@RequestMapping(value="/board/openBoardListJson.do")
    public ModelAndView openBoardListJson(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardListJson");

		return mv;
    }
	/**
	 * JSON Data 처리
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/board/JsonBoardList.do")
	public ModelAndView jsonBoardList(CommandMap commandMap) throws Exception {
		// action-servlet에서 선언한 bean의 이름
		ModelAndView mv = new ModelAndView("jsonView");
		
		List<Map<String, Object>> list = sampleService.selectBoardListJson(commandMap.getMap());
		mv.addObject("list", list);
		if(list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));			
		} else {
			mv.addObject("TOTAL", 0);
		}
		
		return mv;
	}
	
	@RequestMapping(value="/board/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/board/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/board/openBoardList.do");
		
		sampleService.insertBoard(commandMap.getMap(), req);
		
		return mv;
	}
	
	@RequestMapping(value="/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}


	@RequestMapping(value="/board/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardUpdate");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/board/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/board/openBoardDetail.do");
		
		sampleService.updateBoard(commandMap.getMap(), req);
		
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/board/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/board/openBoardList.do");
		
		sampleService.deleteBoard(commandMap.getMap());
		
		return mv;
	}

	@RequestMapping(value="/sample/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap)throws Exception {
		ModelAndView mv = new ModelAndView("");
		
		if(commandMap.isEmpty() == false) {
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;
			while(iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openSampleList.do")
	public ModelAndView openSampleList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		return mv;
	}
}