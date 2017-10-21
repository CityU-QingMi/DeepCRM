    @Test
    public void testAnnotated_Error() throws Exception
    {
        AnnotatedTextSocket socket = new AnnotatedTextSocket();
        EventDriver driver = wrap(socket);

        try (LocalWebSocketSession conn = new CloseableLocalWebSocketSession(container,testname,driver))
        {
            conn.open();
            driver.incomingError(new WebSocketException("oof"));
            driver.incomingFrame(new CloseInfo(StatusCode.NORMAL).asFrame());

            socket.capture.assertEventCount(3);
            socket.capture.pop().assertEventStartsWith("onConnect");
            socket.capture.pop().assertEventStartsWith("onError(WebSocketException: oof)");
            socket.capture.pop().assertEventStartsWith("onClose(1000,");
        }
    }
