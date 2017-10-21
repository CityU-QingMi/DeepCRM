    @Override
    public void onFrame(Frame frame)
    {
        if (listener instanceof WebSocketFrameListener)
        {
            ((WebSocketFrameListener)listener).onWebSocketFrame(new ReadOnlyDelegatedFrame(frame));
        }

        if (listener instanceof WebSocketPingPongListener)
        {
            if (frame.getType() == Type.PING)
            {
                ((WebSocketPingPongListener)listener).onWebSocketPing(frame.getPayload().asReadOnlyBuffer());
            }
            else if (frame.getType() == Type.PONG)
            {
                ((WebSocketPingPongListener)listener).onWebSocketPong(frame.getPayload().asReadOnlyBuffer());
            }
        }
    }
