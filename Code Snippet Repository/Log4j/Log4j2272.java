    @Test
    public void testLookup() {
        final Message msg = new StructuredDataMessage("Test", "This is a test", "Audit");
        final LogEvent event = Log4jLogEvent.newBuilder().setLevel(Level.DEBUG).setMessage(msg).build();
        final StrLookup lookup = new StructuredDataLookup();
        String value = lookup.lookup(event, TESTKEY);
        assertEquals(TESTVAL, value);
        value = lookup.lookup("BadKey");
        assertNull(value);
    }
