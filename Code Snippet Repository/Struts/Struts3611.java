    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();

        if (action instanceof ValidationAware) {
            ValidationAware validationAwareAction = (ValidationAware) action;

            if (validationAwareAction.hasErrors()) {
          		LOG.debug("Errors on action {}, returning result name 'input'", validationAwareAction);
            	ActionMapping mapping = (ActionMapping) ActionContext.getContext().get(ServletActionContext.ACTION_MAPPING);
            	String method = inputResultName;
                if (postMethodName.equals(mapping.getMethod())) {
                   method = newMethodName;
                } else if (putMethodName.equals(mapping.getMethod())) {
                   method = editMethodName;
                }
                
                
            	HttpHeaders info = new DefaultHttpHeaders()
            	    .disableCaching()
            	    .renderResult(method)
            	    .withStatus(validationFailureStatusCode);
            	
            	Map<String, Object> errors = new HashMap<>();
            	
            	errors.put("actionErrors", validationAwareAction.getActionErrors());
            	errors.put("fieldErrors", validationAwareAction.getFieldErrors());
            	return manager.handleResult(invocation, info, errors);
            }
        }

        return invocation.invoke();
    }
