    @Test
    public void testAppendMethodPut() throws Exception {
        wireMockRule.stubFor(put(urlEqualTo("/test/log4j/1234"))
            .willReturn(SUCCESS_RESPONSE));

        final Appender appender = HttpAppender.newBuilder()
            .withName("Http")
            .withLayout(JsonLayout.createDefaultLayout())
            .setConfiguration(ctx.getConfiguration())
            .setMethod("PUT")
            .setUrl(new URL("http://localhost:" + wireMockRule.port() + "/test/log4j/1234"))
            .build();
        appender.append(createLogEvent());

        wireMockRule.verify(putRequestedFor(urlEqualTo("/test/log4j/1234"))
            .withHeader("Host", containing("localhost"))
            .withHeader("Content-Type", containing("application/json"))
            .withRequestBody(containing("\"message\" : \"" + LOG_MESSAGE + "\"")));
    }
