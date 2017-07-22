package com.niit.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.niit.Dao.UserDao;
import com.niit.model.User;





@Controller

public class HomeController {

	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/")
	public String showIndex() {
		return "Login";
	}

@RequestMapping("/login")
	public ModelAndView login(Model mod,@ModelAttribute User user, @RequestParam(value = "email") String email,
			@RequestParam(value="password")String password)
	{	
		
		ModelAndView mv = new ModelAndView("Login");
		
		user=null;
	
		user = userDao.isvalidUser(email, password);
		if( user == null){
			mv=new ModelAndView("error");
		
		}else if(user.getRole().equals("ROLE_USER")){
		        mv = new ModelAndView("index");
			}
		
		
		return mv;
		
	}}
	
