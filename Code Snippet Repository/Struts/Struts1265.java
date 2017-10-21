    private ValueStackFactory createValueStackFactory(final Map<String, Object> context) {
        OgnlValueStackFactory factory = new OgnlValueStackFactory() {
            @Override
            public ValueStack createValueStack(ValueStack stack) {
                return createStubValueStack(context);
            }
        };
        container.inject(factory);
        return factory;
    }
