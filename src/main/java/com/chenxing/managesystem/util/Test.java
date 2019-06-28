package com.chenxing.managesystem.util;

import com.whalin.MemCached.SockIOPool;

public class Test {
	public static void main(String[] args) {

		String[] servers = { "172.16.176.51:9099", "172.16.14.103:9099" };
		Integer[] weights = { 1, 1 };

		com.chenxing.managesystem.util.memcached.SchoonerSockIOPool pool = com.chenxing.managesystem.util.memcached.SchoonerSockIOPool
				.getInstance("default");
		pool.setServers(servers);
		// pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		pool.setHashingAlg(SockIOPool.CONSISTENT_HASH); // 3
		pool.initialize();

		String test = pool.selectNodes("123", 0);

		System.out.println(test);
	}
}