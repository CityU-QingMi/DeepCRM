    @Test
    public void flowTracing_SupplierOfThreadDumpMessage() {
        logger.traceEntry(new Supplier<ThreadDumpMessage>() {
            @Override
            public ThreadDumpMessage get() {
                return new ThreadDumpMessage("Title of ...");
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("RUNNABLE"));
        assertThat("Missing entry data", results.get(0), containsString("Title of ..."));
        assertThat("Missing entry data", results.get(0), containsString(getClass().getName()));
    }
