    private void checkValidateAnnotatedMethodOnly(boolean validateAnnotatedMethodOnly, String methodName,
    		int expectedFooValidators, int expectedStatusValidators, int expectedResultValidators) {
		Form form = new Form(stack, request, response);
        container.inject(form);
        form.getParameters().put("actionClass", TestAction.class);

        form.setAction("actionName" + (methodName != null ? "!" + methodName : ""));
        validationInterceptor.setValidateAnnotatedMethodOnly(validateAnnotatedMethodOnly);

        List v = form.getValidators("foo");
        assertEquals(expectedFooValidators, v.size());
        for (Object validator : v) {
        	assertEquals(RequiredFieldValidator.class, validator.getClass());
		}

        v = form.getValidators("status");
        assertEquals(expectedStatusValidators, v.size());
        for (Object validator : v) {
        	assertEquals(RequiredFieldValidator.class, validator.getClass());
		}

        v = form.getValidators("result");
        assertEquals(expectedResultValidators, v.size());
        for (Object validator : v) {
        	assertEquals(RequiredStringValidator.class, validator.getClass());
		}
	}
