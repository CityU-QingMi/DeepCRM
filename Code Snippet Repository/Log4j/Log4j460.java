    @Test
    public void testBuilder() {
        final String testMsg = "Test message {}";
        final StructuredDataMessage msg = new StructuredDataMessage("MsgId@12345", testMsg, "Alert")
                .with("message", testMsg)
                .with("project", "Log4j")
                .with("memo", "This is a very long test memo to prevent regression of LOG4J2-114");
        final String result = msg.getFormattedMessage();
        final String expected = "Alert [MsgId@12345 memo=\"This is a very long test memo to prevent regression of LOG4J2-114\" message=\"Test message {}\" project=\"Log4j\"] Test message {}";
        assertEquals(expected, result);
    }
