    @Override
    public void configure(WebSocketServletFactory factory)
    {
        // Test cases 9.x uses BIG frame sizes, let policy handle them.
        int bigFrameSize = 20 * MBYTE;

        factory.getPolicy().setMaxTextMessageSize(bigFrameSize);
        factory.getPolicy().setMaxBinaryMessageSize(bigFrameSize);

        factory.register(ABSocket.class);
    }
