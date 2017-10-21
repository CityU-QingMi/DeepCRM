    @Test
    public void testAdapter_ConnectClose() throws Exception
    {
        AdapterConnectCloseSocket socket = new AdapterConnectCloseSocket();
        EventDriver driver = wrap(socket);

        try (LocalWebSocketSession conn = new CloseableLocalWebSocketSession(container,testname,driver))
        {
            conn.open();
            driver.incomingFrame(new CloseInfo(StatusCode.NORMAL).asFrame());

            socket.capture.assertEventCount(2);
            socket.capture.pop().assertEventStartsWith("onWebSocketConnect");
            socket.capture.pop().assertEventStartsWith("onWebSocketClose");
        }
    }
