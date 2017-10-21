    @Test
    public void testLayoutLoggerName() throws Exception {
        final boolean propertiesAsList = false;
        // @formatter:off
        final AbstractJacksonLayout layout = JsonLayout.newBuilder()
                .setLocationInfo(false)
                .setProperties(false)
                .setPropertiesAsList(propertiesAsList)
                .setComplete(false)
                .setCompact(true)
                .setEventEol(false)
                .setCharset(StandardCharsets.UTF_8)
                .setIncludeStacktrace(true)
                .build();
        // @formatter:on
        // @formatter:off
        final Log4jLogEvent expected = Log4jLogEvent.newBuilder()
                .setLoggerName("a.B")
                .setLoggerFqcn("f.q.c.n")
                .setLevel(Level.DEBUG)
                .setMessage(new SimpleMessage("M"))
                .setThreadName("threadName")
                .setTimeMillis(1).build();
        // @formatter:on
        final String str = layout.toSerializable(expected);
        assertTrue(str, str.contains("\"loggerName\":\"a.B\""));
        final Log4jLogEvent actual = new Log4jJsonObjectMapper(propertiesAsList, true, false).readValue(str, Log4jLogEvent.class);
        assertEquals(expected.getLoggerName(), actual.getLoggerName());
        assertEquals(expected, actual);
    }
