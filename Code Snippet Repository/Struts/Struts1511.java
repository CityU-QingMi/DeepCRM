    public void testSkipAllActionLevelShortCircuit2() {
        // get validators
        List validatorList = annotationActionValidatorManager.getValidators(AnnotationUser.class, null);
        assertEquals(10, validatorList.size());

        try {
            AnnotationUser user = new AnnotationUser();
            user.setName("Mark");
            // * mark both email to starts with mark to get pass the action-level validator,
            // so we could concentrate on testing the field-level validators (AnnotationUser-validation.xml)
            // * make both email the same to pass the action-level validator at 
            // AnnotationUserMarker-validation.xml
            user.setEmail("mark_bad_email_for_field_val@foo.com");
            user.setEmail2("mark_bad_email_for_field_val@foo.com");

            ValidatorContext context = new DummyValidatorContext(user, tpf);
            annotationActionValidatorManager.validate(user, null, context);
            assertTrue(context.hasFieldErrors());

            // check field errors
            // we have an error in this field level, email does not ends with mycompany.com
            List l = context.getFieldErrors().get("email");
            assertNotNull(l);
            assertEquals(1, l.size()); // because email-field-val is short-circuit
            assertEquals("Email not from the right company.", l.get(0));

            
            // check action errors
            l = (List) context.getActionErrors();
            assertFalse(context.hasActionErrors());
            assertEquals(0, l.size());
            
            
        } catch (ValidationException ex) {
            ex.printStackTrace();
            fail("Validation error: " + ex.getMessage());
        }
    }
