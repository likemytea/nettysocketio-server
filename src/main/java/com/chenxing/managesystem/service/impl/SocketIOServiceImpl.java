package com.chenxing.managesystem.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chenxing.managesystem.domain.PushMessage;
import com.chenxing.managesystem.service.IMService;
import com.chenxing.managesystem.service.SocketIOService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

@Service(value = "socketIOService")
public class SocketIOServiceImpl implements SocketIOService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	// 用来存已连接的客户端
	private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

	@Autowired
	private SocketIOServer socketIOServer;

	@Autowired
	private IMService iMService;

	/**
	 * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	private void autoStartup() throws Exception {
		start();
	}

	/**
	 * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
	 * 
	 * @throws Exception
	 */
	@PreDestroy
	private void autoStop() throws Exception {
		stop();
	}

	private void printEventLog(String eventType, String loginid, Map<String, SocketIOClient> map) {

		log.info("===SOCKET IO CONN EVNET======>eventType:{},loginid:{},mapContent:{}", eventType, loginid, map.size());
		Iterator<Map.Entry<String, SocketIOClient>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, SocketIOClient> entry = it.next();
			log.info("key:{},value:{}", entry.getKey(), entry.getValue());
		}

	}

	@Override
	public void start() {
		// 监听客户端连接
		socketIOServer.addConnectListener(client -> {
			String loginId = getParamsByClient(client);
			if (loginId != null) {
				clientMap.put(loginId, client);
			}
			printEventLog("connect", loginId, clientMap);
		});

		// 监听客户端断开连接
		socketIOServer.addDisconnectListener(client -> {
			String loginId = getParamsByClient(client);
			if (loginId != null) {
				clientMap.remove(loginId);
				client.disconnect();
			}
			printEventLog("disconnect", loginId, clientMap);
			tishi(loginId);
		});

		// 处理自定义的事件，与连接监听类似
		socketIOServer.addEventListener(PUSH_EVENT, PushMessage.class, (client, data, ackSender) -> {
			if ("getMsg".equals(data.getActionType())) {
				String rtnData = iMService.getChatMsg(data.getSender(), data.getChatPerson());
				ackSender.sendAckData(rtnData);
			} else {
				int isSucc = pushMessageToUser(data);
				ackSender.sendAckData(isSucc);
			}
		});
		socketIOServer.start();
	}

	/**
	 * 某用户离线时，向所有在线用户发送通知
	 * 
	 */
	private void tishi(String loginId) {
		PushMessage mess = new PushMessage();
		mess.setContent(loginId + "离线！");

		Iterator<Map.Entry<String, SocketIOClient>> iterator = clientMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, SocketIOClient> entry = iterator.next();
			mess.setChatPerson(entry.getKey());
			pushMessageToUser(mess);
		}

	}

	@Override
	public void stop() {
		if (socketIOServer != null) {
			socketIOServer.stop();
			socketIOServer = null;
		}
	}

	@Override
	public int pushMessageToUser(PushMessage pushMessage) {
		int sendSucess = 2;
		String receiver = String.valueOf(pushMessage.getChatPerson());

		if (!StringUtils.isEmpty(receiver)) {
			SocketIOClient client = clientMap.get(receiver);
			if (client != null) {
				client.sendEvent(PUSH_EVENT, pushMessage);
				sendSucess = 1;
			}

		}
		return sendSucess;
	}

	/**
	 * 此方法为获取client连接中的参数，可根据需求更改
	 * 
	 * @param client
	 * @return
	 */
	private String getParamsByClient(SocketIOClient client) {
		// 从请求的连接中拿出参数（这里的loginUserNum必须是唯一标识）
		Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
		List<String> list = params.get("sender");
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}