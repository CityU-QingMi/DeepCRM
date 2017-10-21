    @Override
    protected void setUp() throws Exception {
        super.setUp();

        action = container.inject(VisitorValidatorTestAction.class);
        TextProviderFactory tpf = container.getInstance(TextProviderFactory.class);
        ValidatorContext vc1 = new DelegatingValidatorContext(action, tpf);

        VisitorFieldValidator.AppendingValidatorContext vc2 = new AppendingValidatorContext(vc1, createTextProvider(action, vc1), FIRST_NAME, "");
        validatorContext = new AppendingValidatorContext(vc2, createTextProvider(action, vc2), SECOND_NAME, "");
    }
