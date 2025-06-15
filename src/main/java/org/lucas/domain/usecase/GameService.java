package org.lucas.domain.usecase;

import org.lucas.domain.model.ClientContext;
import org.lucas.port.in.tcp.GamePort;

public class GameService implements GamePort {
    @Override
    public void handleRawPacket(byte[] data, ClientContext context) {
        System.out.println("Pacote recebido no domínio:");
        for (byte b : data)
            System.out.printf("%02X ", b);
        System.out.println();
    }
}
