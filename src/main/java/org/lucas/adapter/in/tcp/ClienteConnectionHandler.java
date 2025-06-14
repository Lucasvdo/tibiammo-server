package org.lucas.adapter.in.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

public class ClienteConnectionHandler implements Runnable {

    private final Logger LOGGER = LoggerFactory.getLogger(ClienteConnectionHandler.class);
    private final Socket socket;

    public ClienteConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        LOGGER.info("Socket connection established");
    }
}
