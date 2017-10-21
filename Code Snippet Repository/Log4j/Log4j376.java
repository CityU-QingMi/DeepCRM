    @Test
    public void flowTracingString_ObjectArray2() {
        final EntryMessage msg = logger.traceEntry("doFoo(a={}, b={})", 1, 2);
        logger.traceExit(msg, 3);
        assertEquals(2, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("doFoo(a=1, b=2)"));
        assertThat("Incorrect Exit", results.get(1), startsWith("EXIT[ FLOW ] TRACE Exit"));
        assertThat("Missing exit data", results.get(1), containsString("doFoo(a=1, b=2): 3"));
    }
