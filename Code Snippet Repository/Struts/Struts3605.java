    @SuppressWarnings("")
    protected void selectTarget() {

        // Select target (content to return)
        Throwable e = (Throwable)stack.findValue("exception");
        if (e != null) {

            // Exception
            target = e;
            hasErrors = true;

        } else if (action instanceof ValidationAware && ((ValidationAware)action).hasErrors()) {

            // Error messages
            ValidationAware validationAwareAction = ((ValidationAware)action);

            Map errors = new HashMap();
            if (validationAwareAction.getActionErrors().size() > 0) {
                errors.put("actionErrors", validationAwareAction.getActionErrors());
            }
            if (validationAwareAction.getFieldErrors().size() > 0) {
                errors.put("fieldErrors", validationAwareAction.getFieldErrors());
            }
            target = errors;
            hasErrors = true;

        } else if (action instanceof ModelDriven) {

            // Model
            target = ((ModelDriven)action).getModel();

        } else {
            target = action;
        }

        if (shouldRestrictToGET()) {
            target = null;
        }
    }
