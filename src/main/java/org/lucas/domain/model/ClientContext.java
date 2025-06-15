package org.lucas.domain.model;

import java.net.Socket;

public class ClientContext {

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
}
