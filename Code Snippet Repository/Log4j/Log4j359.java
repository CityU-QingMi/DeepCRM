    @Test
    public void flowTracing_SupplierOfSimpleMessage() {
        logger.traceEntry(new Supplier<SimpleMessage>() {
            @Override
            public SimpleMessage get() {
                return new SimpleMessage("1234567890");
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("(1234567890)"));
        assertThat("Bad toString()", results.get(0), not(containsString("SimpleMessage")));
    }
