    @Test
    public void testWantClientAuthWithoutAuth() throws Exception
    {
        SslContextFactory serverSSL = new SslContextFactory();
        serverSSL.setKeyStorePath("src/test/resources/keystore.jks");
        serverSSL.setKeyStorePassword("storepwd");
        serverSSL.setWantClientAuth(true);
        startServer(serverSSL, new EmptyServerHandler());

        SslContextFactory clientSSL = new SslContextFactory(true);
        startClient(clientSSL);

        ContentResponse response = client.newRequest("https://localhost:" + connector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
