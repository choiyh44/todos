package com.toast.blog.todo.dao;

import java.util.List;

import com.toast.blog.todo.dto.Todo;

public interface TodosDao {
	List<Todo> selectAll() throws Exception ;
	int insert(Todo todo) throws Exception ;
	int selectLastInsertId() throws Exception ;
	int delete(int id) throws Exception ;
	int update(Todo todo) throws Exception ;

}
