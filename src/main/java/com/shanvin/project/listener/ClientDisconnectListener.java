package com.shanvin.project.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientDisconnectListener implements DisconnectListener {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        logger.info("SocketIOClient disconnect: {}", socketIOClient);
    }

}
