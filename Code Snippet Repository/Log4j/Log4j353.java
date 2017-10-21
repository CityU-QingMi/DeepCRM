    @Test
    public void flowTracing_SupplierOfLocalizedMessage() {
        logger.traceEntry(new Supplier<LocalizedMessage>() {
            @Override
            public LocalizedMessage get() {
                return new LocalizedMessage("int foo={}", 1234567890);
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("(int foo=1234567890)"));
        assertThat("Bad toString()", results.get(0), not(containsString("LocalizedMessage")));
    }
