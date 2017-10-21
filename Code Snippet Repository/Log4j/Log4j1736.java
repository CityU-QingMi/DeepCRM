    @Test
    public void testSocketOptions() throws IOException {
        Assert.assertNotNull(loggerContextRule);
        Assert.assertNotNull(loggerContextRule.getConfiguration());
        final SocketAppender appender = loggerContextRule.getAppender("socket", SocketAppender.class);
        Assert.assertNotNull(appender);
        final TcpSocketManager manager = (TcpSocketManager) appender.getManager();
        Assert.assertNotNull(manager);
        final OutputStream outputStream = manager.getOutputStream();
        Assert.assertFalse(outputStream instanceof NullOutputStream);
        final SocketOptions socketOptions = manager.getSocketOptions();
        Assert.assertNotNull(socketOptions);
        final Socket socket = manager.getSocket();
        Assert.assertNotNull(socket);
        // Test config request
        Assert.assertEquals(false, socketOptions.isKeepAlive());
        Assert.assertEquals(null, socketOptions.isOobInline());
        Assert.assertEquals(false, socketOptions.isReuseAddress());
        Assert.assertEquals(false, socketOptions.isTcpNoDelay());
        Assert.assertEquals(Rfc1349TrafficClass.IPTOS_LOWCOST.value(),
                socketOptions.getActualTrafficClass().intValue());
        Assert.assertEquals(10000, socketOptions.getReceiveBufferSize().intValue());
        Assert.assertEquals(8000, socketOptions.getSendBufferSize().intValue());
        Assert.assertEquals(12345, socketOptions.getSoLinger().intValue());
        Assert.assertEquals(54321, socketOptions.getSoTimeout().intValue());
        // Test live socket
        Assert.assertEquals(false, socket.getKeepAlive());
        Assert.assertEquals(false, socket.getReuseAddress());
        Assert.assertEquals(false, socket.getTcpNoDelay());
        // Assert.assertEquals(10000, socket.getReceiveBufferSize());
        // This settings changes while we are running, so we cannot assert it.
        // Assert.assertEquals(8000, socket.getSendBufferSize());
        Assert.assertEquals(12345, socket.getSoLinger());
        Assert.assertEquals(54321, socket.getSoTimeout());
    }
