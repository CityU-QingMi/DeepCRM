    public void testValidationAnnotationExpParams() throws Exception {
        // given
        AnnotationActionValidatorManager manager = createValidationManager(AnnotationValidationExpAction.class, Locale.US);

        // when
        List<Validator> validators = manager.getValidators(AnnotationValidationExpAction.class, null);

        // then
        ValueStack valueStack = container.getInstance(ValueStackFactory.class).createValueStack();
        valueStack.push(new AnnotationValidationExpAction());

        assertEquals(validators.size(), 16);
        for (Validator validator : validators) {
            validator.setValueStack(valueStack);
            validate(validator);
        }
    }
