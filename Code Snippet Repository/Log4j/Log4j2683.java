    @Test
    public void testLog4Event() throws IOException {

        final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Log4j", "Test");
        EventLogger.logEvent(msg);

        final Event event = primary.poll();
        Assert.assertNotNull("Event should not be null", event);
        final String body = getBody(event);
        Assert.assertTrue("Channel contained event, but not expected message. Received: " + body,
            body.endsWith("Test Log4j"));
    }
