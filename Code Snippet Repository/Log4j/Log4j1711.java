    @Test
    public void testAppendErrorIgnore() throws Exception {
        wireMockRule.stubFor(post(urlEqualTo("/test/log4j/"))
            .willReturn(FAILURE_RESPONSE));

        StatusLogger.getLogger().registerListener(new StatusListener() {
            @Override
            public void log(final StatusData data) {
                error = data;
            }

            @Override
            public Level getStatusLevel() {
                return Level.ERROR;
            }

            @Override
            public void close() throws IOException { }
        });

        error = null;

        final Appender appender = HttpAppender.newBuilder()
            .withName("Http")
            .withLayout(JsonLayout.createDefaultLayout())
            .setConfiguration(ctx.getConfiguration())
            .setUrl(new URL("http://localhost:" + wireMockRule.port() + "/test/log4j/"))
            .build();
        appender.append(createLogEvent());

        wireMockRule.verify(postRequestedFor(urlEqualTo("/test/log4j/"))
            .withHeader("Host", containing("localhost"))
            .withHeader("Content-Type", containing("application/json"))
            .withRequestBody(containing("\"message\" : \"" + LOG_MESSAGE + "\"")));

        assertNotNull(error);
        assertEquals(Level.ERROR, error.getLevel());
        assertEquals("Unable to send HTTP in appender [Http]", error.getMessage().toString());
    }
