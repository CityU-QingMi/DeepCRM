    @Test
    public void testReplacement() {
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(EncodingPatternConverterTest.class.getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("Test \r\n<div class=\"test\">this</div> & <div class='test'>that</div>"))
                .build();
        final StringBuilder sb = new StringBuilder();
        final LoggerContext ctx = LoggerContext.getContext();
        final String[] options = new String[]{"%msg"};
        final EncodingPatternConverter converter = EncodingPatternConverter
            .newInstance(ctx.getConfiguration(), options);
        assertNotNull("Error creating converter", converter);
        converter.format(event, sb);
        assertEquals(
            "Test \\r\\n&lt;div class=&quot;test&quot;&gt;this&lt;&#x2F;div&gt; &amp; &lt;div class=&apos;test&apos;&gt;that&lt;&#x2F;div&gt;",
            sb.toString());
    }
