    @Test(expected = AppenderLoggingException.class)
    public void testAppendConnectError() throws Exception {
        final Appender appender = HttpAppender.newBuilder()
            .withName("Http")
            .withLayout(JsonLayout.createDefaultLayout())
            .setConfiguration(ctx.getConfiguration())
            .withIgnoreExceptions(false)
            .setUrl(new URL("http://localhost:"+(wireMockRule.port()+1)+"/test/log4j/"))
            .build();
        appender.append(createLogEvent());
    }
