    @Test
    public void testBuilder() {
        final String testMsg = "Test message {}";
        final StringMapMessage msg = new StringMapMessage()
                .with("message", testMsg)
                .with("project", "Log4j");
        final String result = msg.getFormattedMessage();
        final String expected = "message=\"Test message {}\" project=\"Log4j\"";
        assertEquals(expected, result);
    }
