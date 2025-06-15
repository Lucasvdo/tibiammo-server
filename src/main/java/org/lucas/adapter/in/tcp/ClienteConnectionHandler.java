package org.lucas.adapter.in.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import org.lucas.domain.model.ClientContext;
import org.lucas.port.in.tcp.GamePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClienteConnectionHandler implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(ClienteConnectionHandler.class);
    private final Socket socket;
    private final GamePort gamePort;

    public ClienteConnectionHandler(Socket socket, GamePort gamePort) {
        this.gamePort = gamePort;
        this.socket = socket;
    }

    @Override
    public void run() {
        ClientContext clientContext = new ClientContext(this.socket);

        try (final InputStream inputStream = this.socket.getInputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                this.gamePort.handleRawPacket(buffer, clientContext);
            }

        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
            ioException.printStackTrace();
        } finally {
            try {
                socket.close();
                logger.info("Socket closed");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
        }
    }
}
