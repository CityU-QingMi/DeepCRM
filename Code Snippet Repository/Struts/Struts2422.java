    @SuppressWarnings("")
    private void addBeanValidationErrors(Set<ConstraintViolation<Object>> constraintViolations, Object action) {
        if (constraintViolations != null) {
            ValidatorContext validatorContext = new DelegatingValidatorContext(action, textProviderFactory);
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                String key = constraintViolation.getMessage();
                String message = key;
                try {
                    message = validatorContext.getText(key);
                    if (convertToUtf8 && StringUtils.isNotBlank(message)) {
                        message = new String(message.getBytes(convertFromEncoding), "UTF-8");
                    }
                } catch (Exception e) {
                    LOG.error("Error while trying to fetch message: {}", key, e);
                }

                if (isActionError(constraintViolation)) {
                    LOG.debug("Adding action error [{}]", message);
                    validatorContext.addActionError(message);
                } else {
                    ValidationError validationError = buildBeanValidationError(constraintViolation, message);
                    String fieldName = validationError.getFieldName();
                    if (action instanceof ModelDriven && fieldName.startsWith(ValidatorConstants.MODELDRIVEN_PREFIX)) {
                        fieldName = fieldName.replace("model.", ValidatorConstants.EMPTY_SPACE);
                    }
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Adding field error [{}] with message [{}]", fieldName, validationError.getMessage());
                    }
                    validatorContext.addFieldError(fieldName, validationError.getMessage());
                }
            }
        }
    }
