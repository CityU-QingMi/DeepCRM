    public void testActionLevelShortCircuit() throws Exception {
    	
    	List validatorList = annotationActionValidatorManager.getValidators(AnnotationUser.class, null);
        assertEquals(10, validatorList.size());
        
        AnnotationUser user = new AnnotationUser();
        // all fields will trigger error, but sc of action-level, cause it to not appear
        user.setName(null);		

        user.setEmail("rainerh(at)example.com");
        user.setEmail("rainer_h(at)example.com");


        ValidatorContext context = new DummyValidatorContext(user, tpf);
        annotationActionValidatorManager.validate(user, null, context);
    	
    	// check field level errors
        // shouldn't have any because action error prevents validation of anything else
        List l = context.getFieldErrors().get("email2");
        assertNull(l);
    	
    	
        // check action errors
        assertTrue(context.hasActionErrors());
        l = (List) context.getActionErrors();
        assertNotNull(l);
        // we only get one, because AnnotationUserMarker-validation.xml action-level validator
        // already sc it   :-)
        assertEquals(1, l.size()); 
        assertEquals("Email not the same as email2", l.get(0));
    }
