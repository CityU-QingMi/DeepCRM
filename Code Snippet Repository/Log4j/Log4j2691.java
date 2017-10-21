    @Test
    public void testMultiple() {

        for (int i = 0; i < 10; ++i) {
            final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Multiple " + i, "Test");
            msg.put("counter", Integer.toString(i));
            EventLogger.logEvent(msg);
        }
        final boolean[] fields = new boolean[10];
        for (int i = 0; i < 10; ++i) {
            final Event event = primary.poll();
            Assert.assertNotNull("Received " + i + " events. Event " + (i + 1) + " is null", event);
            final String value = event.getHeaders().get("counter");
            Assert.assertNotNull("Missing 'counter' in map " + event.getHeaders() + ", i = " + i, value);
            final int counter = Integer.parseInt(value);
            if (fields[counter]) {
                Assert.fail("Duplicate event");
            } else {
                fields[counter] = true;
            }
        }
        for (int i = 0; i < 10; ++i) {
            Assert.assertTrue("Channel contained event, but not expected message " + i, fields[i]);
        }
    }
