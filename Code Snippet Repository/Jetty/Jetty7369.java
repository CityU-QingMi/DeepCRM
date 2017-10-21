    @Test(expected = ExecutionException.class)
    public void testClientCannotValidateServerCertificate() throws Exception
    {
        // Only run this test for transports over TLS.
        Assume.assumeTrue(EnumSet.of(Transport.HTTPS, Transport.H2).contains(transport));

        startServer(new EmptyServerHandler());

        // Use a default SslContextFactory, requests should fail because the server certificate is unknown.
        client = newHttpClient(provideClientTransport(transport), new SslContextFactory());
        QueuedThreadPool clientThreads = new QueuedThreadPool();
        clientThreads.setName("client");
        client.setExecutor(clientThreads);
        client.start();

        client.newRequest(newURI())
                .timeout(5, TimeUnit.SECONDS)
                .send();
    }
