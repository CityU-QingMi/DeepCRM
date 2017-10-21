    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        Object action = invocation.getAction();

        if (isJsonEnabled(request)) {
            if (action instanceof ValidationAware) {
                // generate json
                ValidationAware validationAware = (ValidationAware) action;
                if (validationAware.hasErrors()) {
                    return generateJSON(request, response, validationAware);
                }
            }
            if (isValidateOnly(request)) {
                //there were no errors
                setupEncoding(response, request);
                response.getWriter().print("{}");
                response.setContentType("application/json");
                return Action.NONE;
            } else {
                return invocation.invoke();
            }
        } else
            return invocation.invoke();
    }
