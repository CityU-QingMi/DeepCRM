    @Test
    public void rewriteTest() {
        final StructuredDataMessage msg = new StructuredDataMessage("Test", "This is a test", "Service");
        msg.put("Key1", "Value1");
        msg.put("Key2", "Value2");
        EventLogger.logEvent(msg);
        final List<LogEvent> list = app.getEvents();
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events. Expected 1, got " + list.size(), list.size() == 1);
        final LogEvent event = list.get(0);
        final Message m = event.getMessage();
        assertTrue("Message is not a StringMapMessage: " + m.getClass(), m instanceof StructuredDataMessage);
        final StructuredDataMessage message = (StructuredDataMessage) m;
        final Map<String, String> map = message.getData();
        assertNotNull("No Map", map);
        assertTrue("Incorrect number of map entries, expected 3 got " + map.size(), map.size() == 3);
        final String value = map.get("Key1");
        assertEquals("Apache", value);
    }
