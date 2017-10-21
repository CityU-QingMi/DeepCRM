    @Test
    public void testSNIConnectNoWild() throws Exception
    {
        // Use the alternate keystore without wildcard certificates.
        _server.stop();
        _server.removeConnector(_connector);

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath("src/test/resources/snikeystore_nowild");
        sslContextFactory.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
        sslContextFactory.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");

        _connector = new ServerConnector(_server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(_https_config));
        _server.addConnector(_connector);
        _server.start();
        _port = _connector.getLocalPort();

        // The first entry in the keystore is www.example.com, and it will
        // be returned by default, so make sure that here we don't ask for it.
        String response = getResponse("jetty.eclipse.org", "jetty.eclipse.org");
        Assert.assertThat(response, Matchers.containsString("X-HOST: jetty.eclipse.org"));
    }
