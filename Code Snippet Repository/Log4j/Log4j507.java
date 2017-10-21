    @Test
    public void testGetMessageSupplierResultOfSupplier() {
        final Message expected = new SimpleMessage("hi");
        final Message actual = LambdaUtil.get(new MessageSupplier() {
            @Override
            public Message get() {
                return expected;
            }
        });
        assertSame(expected, actual);
    }
