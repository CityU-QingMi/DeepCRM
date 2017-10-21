    protected void setUp() throws Exception {
        super.setUp();
        validator = new CreditCardValidator();
        action = new CreditCardAction();
        TextProviderFactory tpf = container.getInstance(TextProviderFactory.class);
        context = new DummyValidatorContext(action, tpf);
        validator.setValidatorContext(context);

        ValueStack valueStack = container.getInstance(ValueStackFactory.class).createValueStack();
        valueStack.push(action);

        validator.setValueStack(valueStack);
    }
