    @Test
    public void testWithPropertiesAndLocationInfo() {
        final Log4j1XmlLayout layout = Log4j1XmlLayout.createLayout(true, true);

        final Map<String, String> contextMap = new HashMap<>(2);
        contextMap.put("key1", "value1");
        contextMap.put("key2", "value2");
        final Log4jLogEvent event = Log4jLogEvent.newBuilder()
                .setLoggerName("a.B")
                .setLevel(Level.INFO)
                .setMessage(new SimpleMessage("Hello, World"))
                .setTimeMillis(System.currentTimeMillis() + 17)
                .setIncludeLocation(true)
                .setSource(new StackTraceElement("pack.MyClass", "myMethod", "MyClass.java", 17))
                .setContextMap(contextMap)
                .build();

        final String result = layout.toSerializable(event);

        final String expected =
                "<log4j:event logger=\"a.B\" timestamp=\"" + event.getTimeMillis() + "\" level=\"INFO\" thread=\"main\">\r\n" +
                "<log4j:message><![CDATA[Hello, World]]></log4j:message>\r\n" +
                "<log4j:locationInfo class=\"pack.MyClass\" method=\"myMethod\" file=\"MyClass.java\" line=\"17\"/>\r\n" +
                "<log4j:properties>\r\n" +
                "<log4j:data name=\"key1\" value=\"value1\"/>\r\n" +
                "<log4j:data name=\"key2\" value=\"value2\"/>\r\n" +
                "</log4j:properties>\r\n"+
                "</log4j:event>\r\n\r\n";

        assertEquals(expected, result);
    }
