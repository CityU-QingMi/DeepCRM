    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        if (!isProperlyConfigured) {
          throw new IllegalArgumentException("RolesInterceptor is misconfigured, check logs for erroneous configuration!");
        }
        if (!isAllowed(request, invocation.getAction())) {
            LOG.debug("Request is NOT allowed. Rejecting.");
            return handleRejection(invocation, response);
        } else {
            LOG.debug("Request is allowed. Invoking.");
            return invocation.invoke();
        }
    }
