    @Test
    public void testPerformance() throws Exception {
        final long start = System.currentTimeMillis();
        final int count = 10000;
        for (int i = 0; i < count; ++i) {
            final StructuredDataMessage msg = new StructuredDataMessage("Test", "Test Primary " + i, "Test");
            msg.put("counter", Integer.toString(i));
            EventLogger.logEvent(msg);
        }
        final long elapsed = System.currentTimeMillis() - start;
        System.out.println("Time to log " + count + " events " + elapsed + "ms");
    }
