    @Test
    public void testAnnotated_Frames() throws Exception
    {
        AnnotatedFramesSocket socket = new AnnotatedFramesSocket();
        EventDriver driver = wrap(socket);

        try (LocalWebSocketSession conn = new CloseableLocalWebSocketSession(container,testname,driver))
        {
            conn.open();
            driver.incomingFrame(new PingFrame().setPayload("PING"));
            driver.incomingFrame(new TextFrame().setPayload("Text Me"));
            driver.incomingFrame(new BinaryFrame().setPayload("Hello Bin"));
            driver.incomingFrame(new CloseInfo(StatusCode.SHUTDOWN,"testcase").asFrame());

            socket.capture.assertEventCount(6);
            socket.capture.pop().assertEventStartsWith("onConnect(");
            socket.capture.pop().assertEventStartsWith("onFrame(PING[");
            socket.capture.pop().assertEventStartsWith("onFrame(TEXT[");
            socket.capture.pop().assertEventStartsWith("onFrame(BINARY[");
            socket.capture.pop().assertEventStartsWith("onFrame(CLOSE[");
            socket.capture.pop().assertEventStartsWith("onClose(1001,");
        }
    }
