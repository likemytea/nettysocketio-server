package com.chenxing.managesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxing.managesystem.domain.SysUser;
import com.chenxing.managesystem.mapper.SysUserMapper;
import com.chenxing.managesystem.service.SysUserService;

@Service(value = "sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser findByUseName(String userName) {
		SysUser sysUser = sysUserMapper.selectByUserName(userName);
		return sysUser;
	}
}
