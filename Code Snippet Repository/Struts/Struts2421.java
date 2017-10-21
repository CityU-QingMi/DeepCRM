    protected void performBeanValidation(Object action, Validator validator) {

        LOG.trace("Initiating bean validation..");

        Set<ConstraintViolation<Object>> constraintViolations;

        if (action instanceof ModelDriven) {
            LOG.trace("Performing validation on model..");
            Object model = (Object)((ModelDriven<?>) action).getModel();
            constraintViolations = validator.validate(model);
        } else {
            LOG.trace("Performing validation on action..");
            constraintViolations = validator.validate(action);
        }

        addBeanValidationErrors(constraintViolations, action);
    }
