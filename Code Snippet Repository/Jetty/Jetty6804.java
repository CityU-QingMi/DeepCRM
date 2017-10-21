    @Test
    public void testListenerPingPong() throws Exception
    {
        ListenerPingPongSocket socket = new ListenerPingPongSocket();
        EventDriver driver = wrap(socket);

        try (LocalWebSocketSession conn = new CloseableLocalWebSocketSession(container,testname,driver))
        {
            conn.start();
            conn.open();
            driver.incomingFrame(new PingFrame().setPayload("PING"));
            driver.incomingFrame(new PongFrame().setPayload("PONG"));
            driver.incomingFrame(new CloseInfo(StatusCode.NORMAL).asFrame());

            socket.capture.assertEventCount(4);
            socket.capture.pop().assertEventStartsWith("onWebSocketConnect");
            socket.capture.pop().assertEventStartsWith("onWebSocketPing(");
            socket.capture.pop().assertEventStartsWith("onWebSocketPong(");
            socket.capture.pop().assertEventStartsWith("onWebSocketClose(1000,");
        }
    }
