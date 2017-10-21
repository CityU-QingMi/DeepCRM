    public void testHeaderAddedByInterceptor() {

        final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Log4j", "Test");
        EventLogger.logEvent(msg);

        final Event event = primary.poll();
        Assert.assertNotNull("Event should not be null", event);
        final String environmentHeader = event.getHeaders().get("environment");
        Assert.assertEquals("local", environmentHeader);
    }
