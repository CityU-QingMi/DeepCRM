    @Test
    public void testXML() {
        final String testMsg = "Test message {}";
        final StringMapMessage msg = new StringMapMessage();
        msg.put("message", testMsg);
        msg.put("project", "Log4j");
        final String result = msg.getFormattedMessage(new String[]{"XML"});
        final String expected = "<Map>\n  <Entry key=\"message\">Test message {}</Entry>\n" +
            "  <Entry key=\"project\">Log4j</Entry>\n" +
            "</Map>";
        assertEquals(expected, result);
    }
