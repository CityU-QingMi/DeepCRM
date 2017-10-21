    private void doTestParameterNameLengthRestriction(ParametersInterceptor parametersInterceptor,
                                                      int paramNameMaxLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paramNameMaxLength + 1; i++) {
            sb.append("x");
        }

        Map<String, Object> actual = new LinkedHashMap<>();
        parametersInterceptor.setValueStackFactory(createValueStackFactory(actual));
        ValueStack stack = createStubValueStack(actual);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(sb.toString(), "");
        parameters.put("huuhaa", "");

        Action action = new SimpleAction();
        parametersInterceptor.setParameters(action, stack, HttpParameters.create(parameters).build());
        assertEquals(1, actual.size());
    }
