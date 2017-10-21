    @Test
    public void testGetAllReturnsResultOfSuppliers() {
        final String expected1 = "result1";
        final Supplier<String> function1 = new Supplier<String>() {
            @Override
            public String get() {
                return expected1;
            }
        };
        final String expected2 = "result2";
        final Supplier<String> function2 = new Supplier<String>() {
            @Override
            public String get() {
                return expected2;
            }
        };

        final Supplier<?>[] functions = { function1, function2 };
        final Object[] actual = LambdaUtil.getAll(functions);
        assertEquals(actual.length, functions.length);
        assertSame(expected1, actual[0]);
        assertSame(expected2, actual[1]);
    }
