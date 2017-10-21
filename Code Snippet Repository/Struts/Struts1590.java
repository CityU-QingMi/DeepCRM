    @Override
    protected void setUp() throws Exception {
        super.setUp();

        action = new InternalActionSupport();
        container.inject(action);

        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.push(action);

        validator = new StringLengthFieldValidator();
        validator.setFieldName("myField");
        validator.setMessageKey("error");
        validator.setValidatorContext(new DelegatingValidatorContext(action, container.getInstance(TextProviderFactory.class)));
        validator.setMaxLength(5);
        validator.setMinLength(2);
        validator.setValueStack(valueStack);
    }
