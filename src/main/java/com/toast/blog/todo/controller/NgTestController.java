package com.toast.blog.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toast.blog.todo.dto.Todo;
import com.toast.blog.todo.service.TodoService;

@Controller
public class NgTestController {
	private static final Logger logger = LoggerFactory.getLogger(NgTestController.class);

	@Autowired
	private TodoService todoService;

	@RequestMapping(value = "/ngtest/ngtest.nhn")
	public String ngtest(Model model) {
		return "ngtest/ngtest";
	}

	@RequestMapping(value = "/ngtest/ngtest1.nhn")
	public String ngtest1(Locale locale, Model model) {
		return "ngtest/ngtest1";
	}

	@RequestMapping(value = "/ngtest/getAllTodos.nhn")
	public @ResponseBody Map<String, Object> getAllTodos(Locale locale, Model model) {
		logger.info("getAllTodos", locale);
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
			logger.error("", e);
			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", false);
			header.put("resultCode", -1); // 0:성공, -xxxx:실패
			header.put("resultMessage", e.getMessage()); 
			response.put("header", header);

			return response;
		}
	}

	@RequestMapping(value = "/ngtest/addTodo.nhn")
	public @ResponseBody Map<String, Object> addTodo(Locale locale, @ModelAttribute Todo todo) {
		logger.info("addTodo", locale);
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			int id = todoService.insert(todo);
			response.put("id", id);

			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", true);
			header.put("resultCode", 0); // 0:성공, -xxxx:실패
			header.put("resultMessage", ""); 
			response.put("header", header);

			return response;
		} catch (Exception e) {
			logger.error("", e);
			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", false);
			header.put("resultCode", -1); // 0:성공, -xxxx:실패
			header.put("resultMessage", e.getMessage()); 
			response.put("header", header);

			return response;
		}
	}

	@RequestMapping(value = "/ngtest/removeTodo.nhn")
	public @ResponseBody Map<String, Object> removeTodo(Locale locale, @ModelAttribute Todo todo) {
		logger.info("removeTodo", locale);
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			todoService.delete(todo.getId());

			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", true);
			header.put("resultCode", 0); // 0:성공, -xxxx:실패
			header.put("resultMessage", ""); 
			response.put("header", header);

			return response;
		} catch (Exception e) {
			logger.error("", e);
			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", false);
			header.put("resultCode", -1); // 0:성공, -xxxx:실패
			header.put("resultMessage", e.getMessage()); 
			response.put("header", header);

			return response;
		}
	}

	@RequestMapping(value = "/ngtest/updateTodo.nhn")
	public @ResponseBody Map<String, Object> updateTodo(Locale locale, @ModelAttribute Todo todo) {
		logger.info("updateTodo", locale);
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			int id = todoService.update(todo);

			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", true);
			header.put("resultCode", 0); // 0:성공, -xxxx:실패
			header.put("resultMessage", ""); 
			response.put("header", header);

			return response;
		} catch (Exception e) {
			logger.error("", e);
			Map<String, Object> header = new HashMap<String,Object>();
			header.put("isSuccessful", false);
			header.put("resultCode", -1); // 0:성공, -xxxx:실패
			header.put("resultMessage", e.getMessage()); 
			response.put("header", header);

			return response;
		}
	}

}
