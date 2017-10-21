    @Test
    public void testAppendCustomHeader() throws Exception {
        wireMockRule.stubFor(post(urlEqualTo("/test/log4j/"))
            .willReturn(SUCCESS_RESPONSE));

        final Appender appender = HttpAppender.newBuilder()
            .withName("Http")
            .withLayout(JsonLayout.createDefaultLayout())
            .setConfiguration(ctx.getConfiguration())
            .setUrl(new URL("http://localhost:" + wireMockRule.port() + "/test/log4j/"))
            .setHeaders(new Property[] {
                Property.createProperty("X-Test", "header value"),
                Property.createProperty("X-Runtime", "${java:runtime}")
            })
            .build();
        appender.append(createLogEvent());

        wireMockRule.verify(postRequestedFor(urlEqualTo("/test/log4j/"))
            .withHeader("Host", containing("localhost"))
            .withHeader("X-Test", equalTo("header value"))
            .withHeader("X-Runtime", equalTo(JAVA_LOOKUP.getRuntime()))
            .withHeader("Content-Type", containing("application/json"))
            .withRequestBody(containing("\"message\" : \"" + LOG_MESSAGE + "\"")));
    }
