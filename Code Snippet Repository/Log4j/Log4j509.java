    @Test(expected = RuntimeException.class)
    public void testGetAllThrowsExceptionIfAnyOfTheSuppliersThrowsException() {
        final Supplier<String> function1 = new Supplier<String>() {
            @Override
            public String get() {
                return "abc";
            }
        };
        final Supplier<String> function2 = new Supplier<String>() {
            @Override
            public String get() {
                throw new RuntimeException();
            }
        };

        final Supplier<?>[] functions = { function1, function2 };
        LambdaUtil.getAll(functions);
    }
