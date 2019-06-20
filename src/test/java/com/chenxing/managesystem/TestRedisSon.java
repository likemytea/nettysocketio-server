package com.chenxing.managesystem;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.chenxing.managesystem.domain.PushMessage;

public class TestRedisSon {
	public static void main(String[] args) {
		TestRedisSon r = new TestRedisSon();
		RedissonClient client = r.getRedisSonClient();
		RBucket<String> b = client.getBucket("test1");
		b.set("hello32424你好");
		RBucket<PushMessage> getRBucket = client.getBucket("test1");
		System.out.println(getRBucket.get());
		client.shutdown();
	}

	private void saveKEYS() {
		// 存值
		PushMessage m = new PushMessage();
		m.setNickName("aaa");
		RedissonClient client = this.getRedisSonClient();
		RBucket<PushMessage> bucket = client.getBucket("PushMessage");
		bucket.set(m);
		client.shutdown();
	}

	/**
	 * 使用redisson 链接redis. 返回redisson实例
	 * 
	 */
	private RedissonClient getRedisSonClient() {
		Config redissonConfig = new Config();
		redissonConfig.setCodec(new org.redisson.client.codec.StringCodec());
		redissonConfig.useSingleServer().setDatabase(0).setConnectionMinimumIdleSize(2)
				.setAddress("redis://172.16.31.43:6379");
		RedissonClient redissonClient = Redisson.create(redissonConfig);
		return redissonClient;
	}

}
