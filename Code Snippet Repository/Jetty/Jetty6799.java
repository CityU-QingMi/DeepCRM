    @Test
    public void testAnnotated_ByteArray() throws Exception
    {
        AnnotatedBinaryArraySocket socket = new AnnotatedBinaryArraySocket();
        EventDriver driver = wrap(socket);

        try (LocalWebSocketSession conn = new CloseableLocalWebSocketSession(container,testname,driver))
        {
            conn.open();
            driver.incomingFrame(makeBinaryFrame("Hello World",true));
            driver.incomingFrame(new CloseInfo(StatusCode.NORMAL).asFrame());

            socket.capture.assertEventCount(3);
            socket.capture.pop().assertEventStartsWith("onConnect");
            socket.capture.pop().assertEvent("onBinary([11],0,11)");
            socket.capture.pop().assertEventStartsWith("onClose(1000,");
        }
    }
