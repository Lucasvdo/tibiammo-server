package org.lucas.adapter.in.tcp;

import org.lucas.port.in.tcp.ConnectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer implements ConnectionPort {

    private final Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);
    private final int port;

    public TcpServer(int port) {
        this.port = port;
    }

    @Override
    public void startListening() {
        try (final ServerSocket serverSocket = new ServerSocket(this.port)) {
            LOGGER.info("Listening on port {}", this.port);
            while (true) {
                final Socket socket = serverSocket.accept();
                this.LOGGER.info("Accepted connection from {}", socket.getRemoteSocketAddress());
                final Thread handlerThread = new Thread(new ClienteConnectionHandler(socket));
                handlerThread.start();
            }
        }catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
