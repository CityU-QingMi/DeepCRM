    @Test
    public void testSocketCustomization() throws Exception
    {
        final Queue<String> history = new LinkedBlockingQueue<>();

        _connector.addBean(new SocketCustomizationListener()
        {
            @Override
            protected void customize(Socket socket, Class<? extends Connection> connection, boolean ssl)
            {
                history.add("customize connector " + connection + "," + ssl);
            }
        });

        _connector.getBean(SslConnectionFactory.class).addBean(new SocketCustomizationListener()
        {
            @Override
            protected void customize(Socket socket, Class<? extends Connection> connection, boolean ssl)
            {
                history.add("customize ssl " + connection + "," + ssl);
            }
        });

        _connector.getBean(HttpConnectionFactory.class).addBean(new SocketCustomizationListener()
        {
            @Override
            protected void customize(Socket socket, Class<? extends Connection> connection, boolean ssl)
            {
                history.add("customize http " + connection + "," + ssl);
            }
        });

        String response = getResponse("127.0.0.1", null);
        Assert.assertThat(response, Matchers.containsString("X-HOST: 127.0.0.1"));

        Assert.assertEquals("customize connector class org.eclipse.jetty.io.ssl.SslConnection,false", history.poll());
        Assert.assertEquals("customize ssl class org.eclipse.jetty.io.ssl.SslConnection,false", history.poll());
        Assert.assertEquals("customize connector class org.eclipse.jetty.server.HttpConnection,true", history.poll());
        Assert.assertEquals("customize http class org.eclipse.jetty.server.HttpConnection,true", history.poll());
        Assert.assertEquals(0, history.size());
    }
