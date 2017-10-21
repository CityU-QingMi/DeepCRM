    public void testCustomValidationFailureStatusCode() throws Exception {
        RestWorkflowInterceptor wf = new RestWorkflowInterceptor();

        ActionSupport action = new ActionSupport();
        action.addActionError("some error");

        wf.setValidationFailureStatusCode("666");
        Mock mockActionInvocation = new Mock(ActionInvocation.class);
        Mock mockActionProxy = new Mock(ActionProxy.class);
        mockActionProxy.expectAndReturn("getConfig", null);
        mockActionInvocation.expectAndReturn("getAction", action);
        Mock mockContentTypeHandlerManager = new Mock(ContentTypeHandlerManager.class);
        mockContentTypeHandlerManager.expectAndReturn("handleResult", new AnyConstraintMatcher() {
            public boolean matches(Object[] args) {
                DefaultHttpHeaders headers = (DefaultHttpHeaders) args[1];
                return 666 == headers.getStatus();
            }
        }, null);
        wf.setContentTypeHandlerManager((ContentTypeHandlerManager) mockContentTypeHandlerManager.proxy());

        ActionContext.setContext(new ActionContext(new HashMap<String, Object>() {{
            put(ServletActionContext.ACTION_MAPPING, new ActionMapping());
        }}));
        wf.doIntercept((ActionInvocation) mockActionInvocation.proxy());
        mockContentTypeHandlerManager.verify();
        mockActionInvocation.verify();
    }
