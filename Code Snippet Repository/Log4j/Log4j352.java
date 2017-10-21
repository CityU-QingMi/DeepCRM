    @Test
    public void flowTracing_SupplierOfJsonMessage() {
        logger.traceEntry(new Supplier<JsonMessage>() {
            @Override
            public JsonMessage get() {
                return new JsonMessage(System.getProperties());
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("\"java.runtime.name\":"));
        assertThat("Bad toString()", results.get(0), not(containsString("JsonMessage")));
    }
