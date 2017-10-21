    @Test
    public void flowTracing_SupplierOfFormattedMessage() {
        logger.traceEntry(new Supplier<FormattedMessage>() {
            @Override
            public FormattedMessage get() {
                return new FormattedMessage("int foo={}", 1234567890);
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("(int foo=1234567890)"));
        assertThat("Bad toString()", results.get(0), not(containsString("FormattedMessage")));
    }
