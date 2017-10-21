    @Test
    public void testIdleTimeout() throws Throwable
    {
        long timeout = 1000;
        start(new TimeoutHandler(2 * timeout));
        client.stop();
        final AtomicBoolean sslIdle = new AtomicBoolean();
        client = new HttpClient(sslContextFactory)
        {
            @Override
            public ClientConnectionFactory newSslClientConnectionFactory(ClientConnectionFactory connectionFactory)
            {
                return new SslClientConnectionFactory(getSslContextFactory(), getByteBufferPool(), getExecutor(), connectionFactory)
                {
                    @Override
                    protected SslConnection newSslConnection(ByteBufferPool byteBufferPool, Executor executor, EndPoint endPoint, SSLEngine engine)
                    {
                        return new SslConnection(byteBufferPool, executor, endPoint, engine)
                        {
                            @Override
                            protected boolean onReadTimeout()
                            {
                                sslIdle.set(true);
                                return super.onReadTimeout();
                            }
                        };
                    }
                };
            }
        };
        client.setIdleTimeout(timeout);
        client.start();

        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .send();
            Assert.fail();
        }
        catch (Exception x)
        {
            Assert.assertFalse(sslIdle.get());
            Assert.assertThat(x.getCause(), Matchers.instanceOf(TimeoutException.class));
        }
    }
