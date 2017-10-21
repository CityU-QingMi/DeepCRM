    @Test
    public void testMultiple() throws IOException {

        for (int i = 0; i < 10; ++i) {
            final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Multiple " + i, "Test");
            EventLogger.logEvent(msg);
        }
        for (int i = 0; i < 10; ++i) {
            final Event event = primary.poll();
            Assert.assertNotNull(event);
            final String body = getBody(event);
            final String expected = "Test Multiple " + i;
            Assert.assertTrue("Channel contained event, but not expected message. Received: " + body,
                body.endsWith(expected));
        }
    }
