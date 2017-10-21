    public void testValidationAnnotation() throws Exception {
        // given
        AnnotationActionValidatorManager manager = createValidationManager(AnnotationValidationAction.class, Locale.US);

        // when
        List<Validator> validators = manager.getValidators(AnnotationValidationAction.class, null);

        // then
        assertEquals(validators.size(), 18);
        for (Validator validator : validators) {
            validate(validator);
        }
    }
