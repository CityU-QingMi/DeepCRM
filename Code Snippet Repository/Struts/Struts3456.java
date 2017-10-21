	private void addValidationErrors(ConstraintViolation[] violations, Object action, ValueStack valueStack, String parentFieldname) {
		if (violations != null) {
            ValidatorContext validatorContext = new DelegatingValidatorContext(action, textProviderFactory);
            for (ConstraintViolation violation : violations) {
                //translate message
                String key = violation.getMessage();

                String message = key;
                // push context variable into stack, to allow use ${max}, ${min} etc in error messages
                valueStack.push(violation.getMessageVariables());
                //push the validator into the stack
                valueStack.push(violation.getContext());
                try {
                    message = validatorContext.getText(key);
                } finally {
                    valueStack.pop();
                    valueStack.pop();
                }

                if (isActionError(violation)) {
                	LOG.debug("Adding action error '{}'", message);
                    validatorContext.addActionError(message);
                } else {
                    ValidationError validationError = buildValidationError(violation, message);

                    // build field name
                    String fieldName = validationError.getFieldName();
                    if (parentFieldname != null) {
                    	fieldName = parentFieldname + "." + fieldName;
                    }

                	LOG.debug("Adding field error [{}] with message '{}'", fieldName, validationError.getMessage());
                    validatorContext.addFieldError(fieldName, validationError.getMessage());

                    // don't add "model." prefix to fields of model in model driven action
                    if ((action instanceof ModelDriven) && "model".equals(fieldName)) {
                    	fieldName = null;
                    }

                    // add violations of member object fields
                    addValidationErrors(violation.getCauses(), action, valueStack, fieldName);
                }
            }
        }
	}
