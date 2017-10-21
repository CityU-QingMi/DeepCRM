    protected ValidationError buildBeanValidationError(ConstraintViolation<Object> violation, String message) {

        if (violation.getPropertyPath().iterator().next().getName() != null) {
            String fieldName = violation.getPropertyPath().toString();
            String finalMessage = StringUtils.removeStart(message, fieldName + ValidatorConstants.FIELD_SEPERATOR);
            return new ValidationError(fieldName, finalMessage);
        }

        return null;
    }
