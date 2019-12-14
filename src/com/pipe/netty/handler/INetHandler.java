package com.pipe.netty.handler;

import com.pipe.util.text.ITextComponent;

public interface INetHandler {

    void onDisconnect(ITextComponent reason);
}
