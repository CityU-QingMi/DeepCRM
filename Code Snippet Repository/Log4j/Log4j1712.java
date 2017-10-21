    @Test(expected = AppenderLoggingException.class)
    public void testAppendError() throws Exception {
        wireMockRule.stubFor(post(urlEqualTo("/test/log4j/"))
            .willReturn(FAILURE_RESPONSE));

        final Appender appender = HttpAppender.newBuilder()
            .withName("Http")
            .withLayout(JsonLayout.createDefaultLayout())
            .setConfiguration(ctx.getConfiguration())
            .withIgnoreExceptions(false)
            .setUrl(new URL("http://localhost:" + wireMockRule.port() + "/test/log4j/"))
            .build();
        appender.append(createLogEvent());
    }
