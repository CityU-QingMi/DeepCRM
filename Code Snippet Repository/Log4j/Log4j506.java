    @Test
    public void testGetSupplierResultOfSupplier() {
        final String expected = "result";
        final Object actual = LambdaUtil.get(new Supplier<String>() {
            @Override
            public String get() {
                return expected;
            }
        });
        assertSame(expected, actual);
    }
