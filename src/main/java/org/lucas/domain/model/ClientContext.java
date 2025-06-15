package org.lucas.domain.model;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class ClientContext implements Closeable {

    private final Socket socket;

    public ClientContext(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public String getClientAddress() {
        return this.socket.getInetAddress().getHostAddress();
    }

    @Override
    public void close() throws IOException {
        this.socket.close();
    }
}
