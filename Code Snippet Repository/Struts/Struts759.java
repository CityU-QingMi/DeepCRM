    public void beforeResult(ActionInvocation invocation, String resultCode) {
        try {
            LOG.trace("beforeResult start");
            ActionContext ac = invocation.getInvocationContext();
            if (invocation.getAction() instanceof CookieProvider) {
                HttpServletResponse response = (HttpServletResponse) ac.get(StrutsStatics.HTTP_RESPONSE);
                addCookiesToResponse((CookieProvider) invocation.getAction(), response);
            }
            LOG.trace("beforeResult end");
        } catch (Exception ex) {
            LOG.error("Unable to setup cookies", ex);
        }
    }
