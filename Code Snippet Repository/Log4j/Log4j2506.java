    @Test
    public void testNonVarargArrayParametersWithArity0() {
        class NonVarArgArrayParamsZeroArity {
            @Parameters(arity = "0")
            List<String> params;
        }
        NonVarArgArrayParamsZeroArity params = CommandLine.populateCommand(new NonVarArgArrayParamsZeroArity(), "a", "b", "c");
        assertEquals(new ArrayList<String>(), params.params);

        params = CommandLine.populateCommand(new NonVarArgArrayParamsZeroArity(), "a");
        assertEquals(new ArrayList<String>(), params.params);

        params = CommandLine.populateCommand(new NonVarArgArrayParamsZeroArity());
        assertEquals(null, params.params);
    }
