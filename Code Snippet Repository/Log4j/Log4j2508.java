    @Test
    public void testNonVarargArrayParametersWithArity2() {
        class NonVarArgArrayParamsArity2 {
            @Parameters(arity = "2")
            List<String> params;
        }
        NonVarArgArrayParamsArity2 params = CommandLine.populateCommand(new NonVarArgArrayParamsArity2(), "a", "b", "c");
        assertEquals(Arrays.asList("a", "b"), params.params);

        try {
            params = CommandLine.populateCommand(new NonVarArgArrayParamsArity2(), "a");
            fail("Should not accept input with missing parameter");
        } catch (MissingParameterException ex) {
            assertEquals("positional parameter at index 0..* (params) requires at least 2 values, but only 1 were specified.", ex.getMessage());
        }

        try {
            params = CommandLine.populateCommand(new NonVarArgArrayParamsArity2());
            fail("Should not accept input with missing parameter");
        } catch (MissingParameterException ex) {
            assertEquals("positional parameter at index 0..* (params) requires at least 2 values, but only 0 were specified.", ex.getMessage());
        }
    }
