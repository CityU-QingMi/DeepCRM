    @Test
    public void flowTracing_SupplierOfStringFormattedMessage() {
        logger.traceEntry(new Supplier<StringFormattedMessage>() {
            @Override
            public StringFormattedMessage get() {
                return new StringFormattedMessage("int foo=%,d", 1234567890);
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("(int foo=1,234,567,890)"));
        assertThat("Bad toString()", results.get(0), not(containsString("StringFormattedMessage")));
    }
