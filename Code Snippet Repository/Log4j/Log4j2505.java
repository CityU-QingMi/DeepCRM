    @Test
    public void testNonVarargArrayParametersWithNegativeArityConsumesZeroArguments() {
        class NonVarArgArrayParamsNegativeArity {
            @Parameters(arity = "-1")
            List<String> params;
        }
        NonVarArgArrayParamsNegativeArity params = CommandLine.populateCommand(new NonVarArgArrayParamsNegativeArity(), "a", "b", "c");
        assertEquals(Arrays.asList(), params.params);

        params = CommandLine.populateCommand(new NonVarArgArrayParamsNegativeArity(), "a");
        assertEquals(Arrays.asList(), params.params);

        params = CommandLine.populateCommand(new NonVarArgArrayParamsNegativeArity());
        assertEquals(null, params.params);
    }
