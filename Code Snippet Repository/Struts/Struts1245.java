    public void testParameterNameAware() {
        ParametersInterceptor pi = createParametersInterceptor();
        final Map<String, Object> actual = injectValueStackFactory(pi);
        ValueStack stack = createStubValueStack(actual);
        final Map<String, Object> expected = new HashMap<String, Object>() {
            {
                put("fooKey", "fooValue");
                put("barKey", "barValue");
            }
        };
        Object a = new ParameterNameAware() {
            public boolean acceptableParameterName(String parameterName) {
                return expected.containsKey(parameterName);
            }
        };
        final Map<String, Object> parameters = new HashMap<String, Object>() {
            {
                put("fooKey", "fooValue");
                put("barKey", "barValue");
                put("error-key", "error");
                put("error key", "error");
                put("error:key", "error");
                put("error+key", "error");
                put("test%test", "test%test");
            }
        };
        pi.setParameters(a, stack, HttpParameters.create(parameters).build());
        assertEquals(expected, actual);
    }
