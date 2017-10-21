    @Override
    public void setUp() throws Exception {
        super.setUp();
        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionContext context = new ActionContext(stack.getContext());

        Map<String, Object> conversionErrors = new HashMap<>();
        conversionErrors.put("foo", "bar");
        context.setConversionErrors(conversionErrors);
        validator = new ConversionErrorFieldValidator();
        validationAware = new ValidationAwareSupport();

        DelegatingValidatorContext validatorContext = new DelegatingValidatorContext(validationAware, container.getInstance(TextProviderFactory.class));
        stack.push(validatorContext);
        validator.setValidatorContext(validatorContext);
        validator.setFieldName("foo");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        assertEquals(0, validationAware.getFieldErrors().size());
    }
