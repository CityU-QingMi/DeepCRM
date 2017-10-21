    @Test
    public void flowTracing_SupplierOfMessageFormatMessage() {
        logger.traceEntry(new Supplier<MessageFormatMessage>() {
            @Override
            public MessageFormatMessage get() {
                return new MessageFormatMessage("int foo={0}", 1234567890);
            }
        });
        assertEquals(1, results.size());
        assertThat("Incorrect Entry", results.get(0), startsWith("ENTER[ FLOW ] TRACE Enter"));
        assertThat("Missing entry data", results.get(0), containsString("(int foo=1,234,567,890)"));
        assertThat("Bad toString()", results.get(0), not(containsString("MessageFormatMessage")));
    }
