    @Test
    public void connectionFailsWithoutValidServerCertificate() throws IOException, StoreConfigurationException {
        final TrustStoreConfiguration tsc = new TrustStoreConfiguration(TestConstants.TRUSTSTORE_FILE,
                TestConstants.NULL_PWD, null, null);
        final SslConfiguration sc = SslConfiguration.createSSLConfiguration(null, null, tsc);
        final SSLSocketFactory factory = sc.getSslSocketFactory();
        try {
            try (final SSLSocket clientSocket = (SSLSocket) factory.createSocket(TLS_TEST_HOST, TLS_TEST_PORT)) {
                try (final OutputStream os = clientSocket.getOutputStream()) {
                    os.write("GET config/login_verify2?".getBytes());
                    Assert.fail("Expected IOException");
                } catch (final IOException e) {
                    // Expected, do nothing.
                }
            }
        } catch (final UnknownHostException offline) {
            // this exception is thrown on Windows when offline
        }
    }
