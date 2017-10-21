    @Test
    public void testTextPingText() throws IOException
    {
        LocalWebSocketConnection conn = new LocalWebSocketConnection(testname,bufferPool);
        OutgoingFramesCapture outgoing = new OutgoingFramesCapture();
        WebSocketRemoteEndpoint remote = new WebSocketRemoteEndpoint(conn,outgoing);
        conn.connect();
        conn.open();

        // Start text message
        remote.sendPartialString("Hello ",false);

        // Attempt to send Ping Message
        remote.sendPing(ByteBuffer.wrap(new byte[]
                { 0 }));

        // End text message
        remote.sendPartialString("World!",true);
    }
