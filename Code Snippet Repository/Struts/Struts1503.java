    public void testProfileCallbackThrowsException() throws Exception {
        try {
            UtilTimerStack.profile("p1",
                    new UtilTimerStack.ProfilingBlock<String>() {
                        public String doProfiling() throws Exception {
                            throw new RuntimeException("test");
                        }
                    });
            fail("exception should have been thrown");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }
