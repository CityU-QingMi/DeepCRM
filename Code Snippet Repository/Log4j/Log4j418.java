    @Test
    public void testJSON() {
        final String testMsg = "Test message {}";
        final StringMapMessage msg = new StringMapMessage();
        msg.put("message", testMsg);
        msg.put("project", "Log4j");
        final String result = msg.getFormattedMessage(new String[]{"JSON"});
        final String expected = "{\"message\":\"Test message {}\", \"project\":\"Log4j\"}";
        assertEquals(expected, result);
    }
