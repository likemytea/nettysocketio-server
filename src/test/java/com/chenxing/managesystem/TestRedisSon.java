package com.chenxing.managesystem;

import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.alibaba.fastjson.JSON;
import com.chenxing.managesystem.domain.Msg;
import com.chenxing.managesystem.domain.PushMessage;

public class TestRedisSon {
	public static void main(String[] args) {
		TestRedisSon r = new TestRedisSon();
		RedissonClient client = r.getRedisSonClient();
		RBucket<String> b = client.getBucket("test1");
		b.set("hello32424你好");
		RBucket<PushMessage> getRBucket = client.getBucket("test1");
		System.out.println(getRBucket.get());
		// r.testList2(client);
		// client.getList("liuxing").delete();
		r.testList2(client);
		client.shutdown();
	}

	private void testList1(RedissonClient c) {
		RList<String> list = c.getList("liuxing");
		list.add("yuchen");
		list.add("hanxiao");
		list.add("zhouming");
		list.add("18911027837");
		list.add("15923234562");
		list.add("13222234562");
		list.add("13439364763");
	}

	private void testList3(RedissonClient c) {

		RList<Msg> l = c.getList("liuxing");

		Msg msg = new Msg();
		msg.setUserId("yuchen");
		msg.setMsgId("liuxing-yuchen");
		Map<Long, String> map = new HashMap<Long, String>();
		map.put(System.currentTimeMillis(), "你好，我是helloketty。");
		map.put(System.currentTimeMillis(), "周末了，你在干嘛");
		map.put(System.currentTimeMillis(), "工作么？");
		msg.setContent(map);
		l.add(msg);

		Msg msg2 = new Msg();
		msg2.setUserId("zhouming");
		msg2.setMsgId("liuxing-zhouming");
		Map<Long, String> map2 = new HashMap<Long, String>();
		map2.put(System.currentTimeMillis(), "作业做完了么？");
		map2.put(System.currentTimeMillis(), "你和同学在外面吃吧");
		map2.put(System.currentTimeMillis(), "早点回家哈？");
		msg2.setContent(map2);

		l.add(msg2);

	}

	private void testList2(RedissonClient c) {
		RList<String> list = c.getList("liuxing");
		System.out.println(JSON.toJSONString(list));
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
