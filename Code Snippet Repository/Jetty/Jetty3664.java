    @Test
    public void testOpenWithServerSocketChannel() throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        server.addConnector(connector);
        
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(0));
        
        assertTrue(channel.isOpen());
        int port = channel.socket().getLocalPort();
        assertThat(port,greaterThan(0));
        
        connector.open(channel);
        
        assertThat(connector.getLocalPort(),is(port));
        
        server.start();
        
        assertThat(connector.getLocalPort(),is(port));
        assertThat(connector.getTransport(),is(channel));
        
        server.stop();
        
        assertThat(connector.getTransport(),Matchers.nullValue());
        
        
        
        
        
        
    }
