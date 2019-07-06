package com.chenxing.managesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxing.managesystem.domain.SysUser;
import com.chenxing.managesystem.service.SysUserService;

/**
 * 主页面类
 * <p>
 * Created by liuxing on 17/1/18.
 */
@Controller
public class HomeController {

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/")
	public String goHomePage(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SysUser user = sysUserService.findByUseName(userDetails.getUsername());
		user.setFriendsList(user.getFriends().split(","));
		model.addAttribute("user", user);
		return "homepage";
	}

	@RequestMapping("/login")
	public String login() {
		return "myLogin";
	}

	@RequestMapping("/admin")
	@ResponseBody
	public String hello() {
		return "hello admin";
	}
}
