    private ValueStack createStubValueStack(final Map<String, Object> actual) {
        ValueStack stack = new OgnlValueStack(
                container.getInstance(XWorkConverter.class),
                (CompoundRootAccessor) container.getInstance(PropertyAccessor.class, CompoundRoot.class.getName()),
                container.getInstance(TextProvider.class, "system"), true) {
            @Override
            public void setValue(String expr, Object value) {
                actual.put(expr, value);
            }

            @Override
            public void setParameter(String expr, Object value) {
                actual.put(expr, value);
            }
        };
        container.inject(stack);
        return stack;
    }
