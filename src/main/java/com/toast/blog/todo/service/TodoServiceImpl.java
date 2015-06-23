package com.toast.blog.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toast.blog.todo.dao.TodosDao;
import com.toast.blog.todo.dto.Todo;

@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodosDao todosDao;
	
	public List<Todo> selectAll() throws Exception {
		return todosDao.selectAll();
	}
	
	public int insert(Todo todo) throws Exception {
		todosDao.insert(todo);
		return todosDao.selectLastInsertId();
	}

	public int selectLastInsertId() throws Exception {
		return todosDao.selectLastInsertId();
	}

	public int delete(int id) throws Exception {
		return todosDao.delete(id);
	}
	
	public int update(Todo todo) throws Exception {
		return todosDao.update(todo);
	}


}
