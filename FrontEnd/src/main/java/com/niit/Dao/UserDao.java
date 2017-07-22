package com.niit.Dao;

import org.springframework.stereotype.Repository;

import com.niit.model.User;



@Repository
public interface UserDao {
	public User isvalidUser(String email, String password);
}
