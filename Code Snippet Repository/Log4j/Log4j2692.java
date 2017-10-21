    @Test
    public void testSingle() throws IOException {

        final Logger logger = LogManager.getLogger("EventLogger");
        final Marker marker = MarkerManager.getMarker("EVENT");
        logger.info(marker, "This is a test message");

        final Event event = primary.poll();
        Assert.assertNotNull(event);
        final String body = getBody(event);
        Assert.assertTrue("Channel contained event, but not expected message. Received: " + body,
            body.endsWith("This is a test message"));
    }
