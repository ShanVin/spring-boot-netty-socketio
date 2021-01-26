package com.shanvin.project.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientConnectListener implements ConnectListener {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        logger.info("SocketIOClient connect: {}", socketIOClient);
    }

}
