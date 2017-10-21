    @Test
    public void emptyConfigurationHasDefaultTrustStore() throws IOException {
        final SslConfiguration sc = SslConfiguration.createSSLConfiguration(null, null, null);
        final SSLSocketFactory factory = sc.getSslSocketFactory();
        try {
            try (final SSLSocket clientSocket = (SSLSocket) factory.createSocket(TLS_TEST_HOST, TLS_TEST_PORT)) {
                Assert.assertNotNull(clientSocket);
                clientSocket.close();
            }
        } catch (final UnknownHostException offline) {
            // this exception is thrown on Windows when offline
        }
    }
