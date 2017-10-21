    @Test
    public void testRFC5424Layout() throws IOException {

        final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Log4j", "Test");
        EventLogger.logEvent(msg);

        final Event event = primary.poll();
        Assert.assertNotNull(event);
        final String body = getBody(event);
        Assert.assertTrue("Structured message does not contain @EID: " + body,
            body.contains("Test@18060"));
    }
