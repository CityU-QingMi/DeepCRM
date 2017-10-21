    @Test
    public void testTimestampMessage() {
        final Logger log = context.getLogger("TimestampMessageTest");
        log.info((Message) new TimeMsg("Message with embedded timestamp", 123456789000L));
        final List<String> msgs = app.getMessages();
        assertNotNull(msgs);
        assertEquals(1, msgs.size());
        final String NL = System.lineSeparator();
        assertEquals("123456789000 Message with embedded timestamp" + NL, msgs.get(0));
    }
