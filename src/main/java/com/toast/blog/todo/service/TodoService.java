package com.toast.blog.todo.service;

import java.util.List;

import com.toast.blog.todo.dto.Todo;

public interface TodoService {
	public List<Todo> selectAll() throws Exception ;
	public int insert(Todo todo) throws Exception;
	int selectLastInsertId() throws Exception ;
	public int delete(int id) throws Exception;
	public int update(Todo todo) throws Exception;
}
