package org.lucas.port.in.tcp;

import org.lucas.domain.model.ClientContext;

public interface GamePort {
    void handleRawPacket(byte[] data, ClientContext context);
}
