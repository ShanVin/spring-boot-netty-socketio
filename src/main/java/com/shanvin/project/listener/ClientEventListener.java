package com.shanvin.project.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientEventListener implements DataListener {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Override
    public void onData(SocketIOClient socketIOClient, Object object, AckRequest ackRequest) throws Exception {
        logger.info("SocketIOClient connect: {}", socketIOClient);
    }

}
