    @Test
    public void testListenerBasic_Text() throws Exception
    {
        ListenerBasicSocket socket = new ListenerBasicSocket();
        EventDriver driver = wrap(socket);

        try (LocalWebSocketSession conn = new CloseableLocalWebSocketSession(container,testname,driver))
        {
            conn.start();
            conn.open();
            driver.incomingFrame(new TextFrame().setPayload("Hello World"));
            driver.incomingFrame(new CloseInfo(StatusCode.NORMAL).asFrame());

            socket.capture.assertEventCount(3);
            socket.capture.pop().assertEventStartsWith("onWebSocketConnect");
            socket.capture.pop().assertEventStartsWith("onWebSocketText(\"Hello World\")");
            socket.capture.pop().assertEventStartsWith("onWebSocketClose(1000,");
        }
    }
