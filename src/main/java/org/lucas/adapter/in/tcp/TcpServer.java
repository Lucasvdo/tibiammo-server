package org.lucas.adapter.in.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import org.lucas.port.in.tcp.ConnectionPort;
import org.lucas.port.in.tcp.GamePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpServer implements ConnectionPort {

    private final Logger logger = LoggerFactory.getLogger(TcpServer.class);
    private final int port;
    private final GamePort gamePort;

    public TcpServer(int port, GamePort gamePort) {
        this.port = port;
        this.gamePort = gamePort;
    }

    @Override
    public void startListening() {
        try (final ServerSocket serverSocket = new ServerSocket(this.port)) {
            logger.info("Listening on port {}", this.port);
            while (true) {
                final Socket socket = serverSocket.accept();
                final SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
                this.logger.info("Accepted connection from {}", remoteSocketAddress);
                final Thread thread = new Thread(new ClienteConnectionHandler(socket, gamePort));
                thread.start();
                if (serverSocket.isClosed()) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            this.logger.info("Stopped listening on port {}", this.port);
        }
    }
}
