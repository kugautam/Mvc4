package com.gautam.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gautam.mvc.User;

@Controller
public class UserController 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("/views/registration")
	@ResponseBody
	public String register(@ModelAttribute("user") User user)
	{
		if(jdbcTemplate.update("insert into itransform.user (username,password,email) values(?,?,?)", 
				new Object[] {user.getUserName(),user.getPassWord(),user.geteMail()})==1)
			return "login";
		else 
			return "<h1>Registration Failed<h1>";
	}

	@ResponseBody
	@RequestMapping("/views/login")
	public String authentication(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord)
	{
		if( jdbcTemplate.queryForObject("select password from itransform.user where username=?",new Object[] {userName}, String.class).equals(passWord))
			return "Login Successfull";
		else
			return "<h1>Login Failed<h1>";
	}
}

