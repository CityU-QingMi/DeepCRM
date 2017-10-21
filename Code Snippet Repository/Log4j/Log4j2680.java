    @Test
    public void testFailover() throws InterruptedException, IOException {
        final Logger logger = LogManager.getLogger("testFailover");
        logger.debug("Starting testFailover");
        for (int i = 0; i < 10; ++i) {
            final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Primary " + i, "Test");
            EventLogger.logEvent(msg);
        }
        for (int i = 0; i < 10; ++i) {
            final Event event = primary.poll();
            Assert.assertNotNull(event);
            final String body = getBody(event);
            final String expected = "Test Primary " + i;
            Assert.assertTrue("Channel contained event, but not expected message. Received: " + body,
                body.endsWith(expected));
        }

        // Give the AvroSink time to receive notification and notify the channel.
        Thread.sleep(500);

        primary.stop();


        for (int i = 0; i < 10; ++i) {
            final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Alternate " + i, "Test");
            EventLogger.logEvent(msg);
        }
        for (int i = 0; i < 10; ++i) {
            final Event event = alternate.poll();
            Assert.assertNotNull(event);
            final String body = getBody(event);
            final String expected = "Test Alternate " + i;
/**/
/**/
            Assert.assertTrue("Channel contained event, but not expected message. Expected: " + expected +
                " Received: " + body, body.endsWith(expected));
        }
    }
