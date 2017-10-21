    @Test
    public void testMutableByDesign() { // LOG4J2-763
        final String testMsg = "Test message {}";
        final StructuredDataMessage msg = new StructuredDataMessage("MsgId@1", testMsg, "Alert");

        // modify parameter before calling msg.getFormattedMessage
        msg.put("message", testMsg);
        msg.put("project", "Log4j");
        final String result = msg.getFormattedMessage();
        final String expected = "Alert [MsgId@1 message=\"Test message {}\" project=\"Log4j\"] Test message {}";
        assertEquals(expected, result);

        // modify parameter after calling msg.getFormattedMessage
        msg.put("memo", "Added later");
        final String result2 = msg.getFormattedMessage();
        final String expected2 = "Alert [MsgId@1 memo=\"Added later\" message=\"Test message {}\" project=\"Log4j\"] Test message {}";
        assertEquals(expected2, result2);
    }
