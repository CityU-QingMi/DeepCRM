    @Test
    public void testMutableByDesign() { // LOG4J2-763
        final StringMapMessage msg = new StringMapMessage();

        // modify parameter before calling msg.getFormattedMessage
        msg.put("key1", "value1");
        msg.put("key2", "value2");
        final String result = msg.getFormattedMessage(new String[]{"Java"});
        final String expected = "{key1=\"value1\", key2=\"value2\"}";
        assertEquals(expected, result);

        // modify parameter after calling msg.getFormattedMessage
        msg.put("key3", "value3");
        final String result2 = msg.getFormattedMessage(new String[]{"Java"});
        final String expected2 = "{key1=\"value1\", key2=\"value2\", key3=\"value3\"}";
        assertEquals(expected2, result2);
    }
