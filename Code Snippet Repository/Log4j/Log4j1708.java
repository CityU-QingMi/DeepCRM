    @Test
    public void testAppendHttps() throws Exception {
        wireMockRule.stubFor(post(urlEqualTo("/test/log4j/"))
            .willReturn(SUCCESS_RESPONSE));

        final Appender appender = HttpAppender.newBuilder()
            .withName("Http")
            .withLayout(JsonLayout.createDefaultLayout())
            .setConfiguration(ctx.getConfiguration())
            .setUrl(new URL("https://localhost:" + wireMockRule.httpsPort() + "/test/log4j/"))
            .setSslConfiguration(SslConfiguration.createSSLConfiguration(null,
                KeyStoreConfiguration.createKeyStoreConfiguration(TestConstants.KEYSTORE_FILE, TestConstants.KEYSTORE_PWD, TestConstants.KEYSTORE_TYPE, null),
                TrustStoreConfiguration.createKeyStoreConfiguration(TestConstants.TRUSTSTORE_FILE, TestConstants.TRUSTSTORE_PWD, TestConstants.TRUSTSTORE_TYPE, null)))
            .setVerifyHostname(false)
            .build();
        appender.append(createLogEvent());

        wireMockRule.verify(postRequestedFor(urlEqualTo("/test/log4j/"))
            .withHeader("Host", containing("localhost"))
            .withHeader("Content-Type", containing("application/json"))
            .withRequestBody(containing("\"message\" : \"" + LOG_MESSAGE + "\"")));
    }
