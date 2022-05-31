package com.ace.hexa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ace.hexa.dao.UserDao;
import com.ace.hexa.dto.UserRequestDto;
import com.ace.hexa.dto.UserResponseDto;
import com.ace.hexa.model.UserBean;

@Controller
@RequestMapping("/hexa")
public class LoggingController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/showLogin")
	public ModelAndView showLogin() {
		return new ModelAndView("login", "bean", new UserBean());
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("bean") UserBean user, HttpSession session, HttpServletRequest request) {
		if (userDao.check(user.getUser_email(), user.getUser_password())) {
			UserResponseDto dto = userDao.selectByEmail(user.getUser_email());
			session.setAttribute("userInfo", dto);
			if (dto.getUser_role() == 1) {
				return "redirect:dashboard";
			} else if (dto.getUser_role() == 2) {
				return "redirect:dashboard";
			} else {
				return "home";
			}
		}
		request.setAttribute("error", "<h3>email and password do not match !!</h3>");
//		model.addAttribute("error", "<h3>email and password do not match !!</h3>");
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		session.invalidate();
		return "redirect:/hexa/showLogin";
	}

	@GetMapping("/showRegister")
	public ModelAndView showRegister() {
		return new ModelAndView("register", "bean", new UserBean());
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("bean") UserBean user, HttpSession session, HttpServletRequest request) {
		if (!userDao.checkByEmail(user.getUser_email())) {
			UserRequestDto dto = new UserRequestDto();
			dto.setUser_name(user.getUser_name());
			dto.setUser_email(user.getUser_email());
			dto.setUser_password(user.getUser_password());
			userDao.insertUser(dto);
			return "redirect:/hexa/showLogin";
		}
		request.setAttribute("error", "<h3>A user with that email already exists !!</h3>");
//		model.addAttribute("error", "<h3>A user with that email already exists !!</h3>");
		return "register";
	}

	@GetMapping("/home")
	public String showHome() {
		return "home";
	}

	@GetMapping("/dashboard")
	public String showDashboard() {
		return "adminDashboard";
	}
	
	@GetMapping("/news_manage")
	public String manageNews() {
		return "news_manage";
	}
		
	@GetMapping("/users")
	public String manageUsers() {
		return "users";
	}

}
