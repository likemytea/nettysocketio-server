package com.chenxing.managesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxing.managesystem.service.IMService;

/**
 * 即时通信类
 * <p>
 * Created by liuxing on 19/7/6.
 */
@Controller
@RequestMapping(value = "/im/friends")
public class IMController {
	@Autowired
	private IMService iMService;

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String getFriendsByClientId(@RequestParam String userId) {
		return iMService.getFriendsByClientId(userId);
	}
}
