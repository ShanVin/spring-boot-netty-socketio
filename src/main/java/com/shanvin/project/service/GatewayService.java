package com.shanvin.project.service;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.shanvin.project.config.SocketIOConfig;
import com.shanvin.project.listener.ClientConnectListener;
import com.shanvin.project.listener.ClientDisconnectListener;
import com.shanvin.project.listener.ClientEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class GatewayService {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Autowired
    private SocketIOConfig socketIOConfig;
    @Autowired
    private ClientConnectListener clientConnectListener;
    @Autowired
    private ClientDisconnectListener clientDisconnectListener;
    @Autowired
    private ClientEventListener clientEventListener;

    public void start() throws UnknownHostException {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpKeepAlive(socketIOConfig.isTcpKeepAlive());
        socketConfig.setReuseAddress(socketIOConfig.isReuseAddress());

        Configuration configuration = new Configuration();
        configuration.setBossThreads(socketIOConfig.getBossThreads());
        configuration.setWorkerThreads(socketIOConfig.getWorkerThreads());
        configuration.setUseLinuxNativeEpoll(socketIOConfig.isUseLinuxNativeEpoll());
        configuration.setPingTimeout(socketIOConfig.getPingTimeout());
        configuration.setPingInterval(socketIOConfig.getPingInterval());
        configuration.setMaxHttpContentLength(socketIOConfig.getMaxHttpContentLength());
        configuration.setHostname(InetAddress.getLocalHost().getHostAddress());
        configuration.setPort(socketIOConfig.getPort());
        configuration.setSocketConfig(socketConfig);

        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addConnectListener(clientConnectListener);
        socketIOServer.addDisconnectListener(clientDisconnectListener);
        socketIOServer.addEventListener("event", Object.class, clientEventListener);
        socketIOServer.start();
        logger.info("GatewayService started ...");
    }

}
