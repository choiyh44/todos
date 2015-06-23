package com.toast.blog.todo.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toast.blog.todo.dto.Todo;
import com.toast.blog.todo.service.TodoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TodoService todoService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home.nhn", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
//		Todo todo = new Todo();
//		todo.setName("이름" + new Date());
//		todo.setCompleted(false);
//		todoService.insert(todo);
//		
//		int id = todoService.selectLastInsertId();
//		model.addAttribute("id", id );
		
		List<Todo> todos = todoService.selectAll();
		model.addAttribute("todos", todos );
		
		model.addAttribute("serverTime", formattedDate );

		return "home";
	}
	
	@RequestMapping(value = "/jsonTest.nhn", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> jsonTest(Locale locale, Model model) throws Exception {
		logger.info("jsonTest", locale);
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			List<Todo> todos = todoService.selectAll();
			response.put("todos", todos);
	
			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", true);
			header.put("resultCode", 0); // 0:성공, -xxxx:실패
			header.put("resultMessage", ""); 
			response.put("header", header);

		return response;
		} catch (Exception e) {
			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", true);
			header.put("resultCode", -1); // 0:성공, -xxxx:실패
			header.put("resultMessage", e.getMessage()); 
			response.put("header", header);

			return response;
		}
	}

}
