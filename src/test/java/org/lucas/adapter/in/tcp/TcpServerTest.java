package org.lucas.adapter.in.tcp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import org.junit.jupiter.api.Test;
import org.lucas.domain.model.ClientContext;
import org.lucas.port.in.tcp.GamePort;
import org.mockito.*;

class TcpServerTest {

    @Test
    void shouldForwardPacketToGamePort() throws InterruptedException, IOException {
        GamePort gamePort = Mockito.mock(GamePort.class);
        TcpServer tcpServer = new TcpServer(8181, gamePort);

        new Thread(tcpServer::startListening).start();
        Thread.sleep(5000);

        byte[] testData = new byte[1024];
        testData[0] = (byte) 0xFF;
        testData[1] = (byte) 0xFF;
        testData[2] = (byte) 0xFF;

        try (Socket socket = new Socket("localhost", 8181)) {
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write(testData);
            outputStream.flush();
            Thread.sleep(200);
        }

        ArgumentCaptor<byte[]> dataCaptor = ArgumentCaptor.forClass(byte[].class);
        verify(gamePort, atLeastOnce()).handleRawPacket(dataCaptor.capture(), any(ClientContext.class));

        byte[] capturedData = dataCaptor.getValue();
        assertArrayEquals(testData, capturedData);
        assertEquals(testData.length, capturedData.length);
    }
}
