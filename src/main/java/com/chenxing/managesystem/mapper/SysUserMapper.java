package com.chenxing.managesystem.mapper;

import java.util.List;

import com.chenxing.managesystem.domain.SysRole;
import com.chenxing.managesystem.domain.SysUser;

public interface SysUserMapper {
	SysUser selectByUserName(String userName);
	List<SysRole> getGroupByUserName(String username);
}
