package org.lucas;

import org.lucas.adapter.in.tcp.TcpServer;
import org.lucas.domain.usecase.GameService;
import org.lucas.port.in.tcp.GamePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final GamePort gamePort = new GameService();
        final TcpServer tcpServer = new TcpServer(7171, gamePort);
        logger.info("Iniciando servidor...");
        tcpServer.startListening();
    }
}
