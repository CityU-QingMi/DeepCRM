    @Test
    public void testReplacement() {
        // See org.fusesource.jansi.AnsiRenderer
        logger.error("@|WarningStyle Warning!|@ Pants on @|WarningStyle fire!|@");

        final List<String> msgs = app.getMessages();
        assertNotNull(msgs);
        assertEquals("Incorrect number of messages. Should be 1 is " + msgs.size(), 1, msgs.size());
        assertTrue("Replacement failed - expected ending " + EXPECTED + ", actual " + msgs.get(0),
                msgs.get(0).endsWith(EXPECTED));
    }
