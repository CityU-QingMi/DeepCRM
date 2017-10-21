    @Test
    public void mdc() {

        ThreadContext.put("TestYear", Integer.valueOf(2010).toString());
        logger.debug("Debug message");
        ThreadContext.clearMap();
        logger.debug("Debug message");
        assertEquals(2, results.size());
        assertTrue("Incorrect MDC: " + results.get(0),
            results.get(0).startsWith(" DEBUG Debug message {TestYear=2010}"));
        assertTrue("MDC not cleared?: " + results.get(1),
            results.get(1).startsWith(" DEBUG Debug message"));
    }
