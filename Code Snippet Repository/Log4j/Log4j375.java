    @Test
    public void flowTracingExitValueOnly() {
        logger.traceEntry("doFoo(a={}, b={})", 1, 2);
        logger.traceExit(3);
        assertEquals(2, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("doFoo(a=1, b=2)"));
        assertThat("Incorrect Exit", results.get(1), startsWith("EXIT[ FLOW ] TRACE Exit"));
        assertThat("Missing exit data", results.get(1), containsString("3"));
    }
