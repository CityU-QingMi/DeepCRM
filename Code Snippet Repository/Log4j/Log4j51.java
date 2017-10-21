    @Test
    public void testWithoutThrown() {
        final Log4j1XmlLayout layout = Log4j1XmlLayout.createLayout(false, true);

        final Log4jLogEvent event = Log4jLogEvent.newBuilder()
                .setLoggerName("a.B")
                .setLevel(Level.INFO)
                .setMessage(new SimpleMessage("Hello, World"))
                .setTimeMillis(System.currentTimeMillis() + 17)
                .build();

        final String result = layout.toSerializable(event);

        final String expected =
                "<log4j:event logger=\"a.B\" timestamp=\"" + event.getTimeMillis() + "\" level=\"INFO\" thread=\"main\">\r\n" +
                "<log4j:message><![CDATA[Hello, World]]></log4j:message>\r\n" +
                "</log4j:event>\r\n\r\n";

        assertEquals(expected, result);
    }
