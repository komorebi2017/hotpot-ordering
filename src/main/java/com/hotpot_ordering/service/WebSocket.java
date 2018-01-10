package com.hotpot_ordering.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 21:09
 * @ Description:
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("websocket消息:有新的连接，总数：{}",webSockets.size());
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("websocket消息：连接断开，总数：{}",webSockets.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("websocket消息：收到客户端发来的消息：{}",message);
    }

    public void sendMessage(String message){
        for(WebSocket webSocket: webSockets){
            log.info("websocket消息：广播消息，message={}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
