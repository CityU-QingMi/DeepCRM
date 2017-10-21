    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
                for (final Map.Entry<String, Object> entry : initialBindings.entrySet()) {
                    builder.bind(entry.getKey(), entry.getValue());
                }
                base.evaluate();
            }
        };
    }
