    public void testMessageKeyIsReturnedIfNoOtherDefault() throws ValidationException {
        Validator validator = new ValidatorSupport() {
            public void validate(Object object) throws ValidationException {
                addActionError(object);
            }
        };
        validator.setValueStack(ActionContext.getContext().getValueStack());

        String messageKey = "does.not.exist";
        validator.setMessageKey(messageKey);

        SimpleAction action = new SimpleAction();
        container.inject(action);

        ValidatorContext validatorContext = new DelegatingValidatorContext(action, container.getInstance(TextProviderFactory.class));
        validator.setValidatorContext(validatorContext);
        validator.validate(this);
        assertTrue(validatorContext.hasActionErrors());

        Collection<String> errors = validatorContext.getActionErrors();
        assertEquals(1, errors.size());
        assertEquals(messageKey, errors.toArray()[0]);
    }
