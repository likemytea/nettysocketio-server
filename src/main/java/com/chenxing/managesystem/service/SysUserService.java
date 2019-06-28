package com.chenxing.managesystem.service;

import com.chenxing.managesystem.domain.SysUser;

/**
 * mybatis demo
 * 
 */
public interface SysUserService {

	SysUser findByUseName(String userName);
}
