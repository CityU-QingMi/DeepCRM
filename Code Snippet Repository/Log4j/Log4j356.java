    @Test
    public void flowTracing_SupplierOfObjectArrayMessage() {
        logger.traceEntry(new Supplier<ObjectArrayMessage>() {
            @Override
            public ObjectArrayMessage get() {
                return new ObjectArrayMessage(1234567890);
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing Enter data", results.get(0), containsString("([1234567890])"));
        assertThat("Bad toString()", results.get(0), not(containsString("ObjectArrayMessage")));
    }
