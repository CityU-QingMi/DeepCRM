    @Test
    public void testAnnotated_InputStream() throws IOException, TimeoutException, InterruptedException
    {
        AnnotatedBinaryStreamSocket socket = new AnnotatedBinaryStreamSocket();
        EventDriver driver = wrap(socket);

        try (LocalWebSocketSession conn = new CloseableLocalWebSocketSession(container,testname,driver))
        {
            conn.open();
            driver.incomingFrame(makeBinaryFrame("Hello World",true));
            driver.incomingFrame(new CloseInfo(StatusCode.NORMAL).asFrame());

            socket.capture.assertEventCount(3);
            socket.capture.pop().assertEventStartsWith("onConnect");
            socket.capture.pop().assertEventRegex("^onBinary\\(.*InputStream.*");
            socket.capture.pop().assertEventStartsWith("onClose(1000,");
        }
    }
