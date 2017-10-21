    @Test
    public void flowTracing_SupplierOfObjectMessage() {
        logger.traceEntry(new Supplier<ObjectMessage>() {
            @Override
            public ObjectMessage get() {
                return new ObjectMessage(1234567890);
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("(1234567890)"));
        assertThat("Bad toString()", results.get(0), not(containsString("ObjectMessage")));
    }
