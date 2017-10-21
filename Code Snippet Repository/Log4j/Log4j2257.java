    @Test
    public void testLayoutLoggerName() throws Exception {
        final AbstractJacksonLayout layout = YamlLayout.newBuilder()
                .setLocationInfo(false)
                .setProperties(false)
                .setIncludeStacktrace(true)
                .setCharset(StandardCharsets.UTF_8)
                .build();
        final Log4jLogEvent expected = Log4jLogEvent.newBuilder() //
                .setLoggerName("a.B") //
                .setLoggerFqcn("f.q.c.n") //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("M")) //
                .setThreadName("threadName") //
                .setTimeMillis(1).build();
        final String str = layout.toSerializable(expected);
        assertTrue(str, str.contains("loggerName: \"a.B\""));
        final Log4jLogEvent actual = new Log4jYamlObjectMapper(false, true, false).readValue(str, Log4jLogEvent.class);
        assertEquals(expected.getLoggerName(), actual.getLoggerName());
        assertEquals(expected, actual);
    }
