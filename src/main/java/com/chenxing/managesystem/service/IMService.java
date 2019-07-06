package com.chenxing.managesystem.service;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.chenxing.managesystem.domain.Msg;

/**
 * 即时通信 获取朋友列表
 * 
 * * Created by liuxing.
 */
@Service("iMService")
@Transactional
public class IMService {
	// private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedissonClient r;

	/**
	 * 根据userid获取朋友列表
	 */
	@Transactional(readOnly = true)
	public String getFriendsByClientId(String userId) {
		RList<Msg> lst = r.getList(userId);
		return JSON.toJSONString(lst);
	}
}
