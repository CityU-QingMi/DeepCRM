    @Test
    public void testTextBinaryText() throws IOException
    {
        LocalWebSocketConnection conn = new LocalWebSocketConnection(testname,bufferPool);
        OutgoingFramesCapture outgoing = new OutgoingFramesCapture();
        WebSocketRemoteEndpoint remote = new WebSocketRemoteEndpoint(conn,outgoing);
        conn.connect();
        conn.open();

        // Start text message
        remote.sendPartialString("Hello ",false);

        try
        {
            // Attempt to start Binary Message
            ByteBuffer bytes = ByteBuffer.wrap(new byte[]
                    { 0, 1, 2 });
            remote.sendPartialBytes(bytes,false);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        }
        catch (IllegalStateException e)
        {
            // Expected path
            Assert.assertThat("Exception",e.getMessage(),containsString("Cannot send"));
        }

        // End text message
        remote.sendPartialString("World!",true);
    }
