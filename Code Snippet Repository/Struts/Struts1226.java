    private void setUpWithExceptionMappings() {
        ActionConfig actionConfig = new ActionConfig.Builder("", "", "")
                .addExceptionMapping(new ExceptionMappingConfig.Builder("xwork", "com.opensymphony.xwork2.XWorkException", "spooky").build())
                .addExceptionMapping(new ExceptionMappingConfig.Builder("throwable", "java.lang.Throwable", "throwable").build())
                .build();
        Mock actionProxy = new Mock(ActionProxy.class);
        actionProxy.expectAndReturn("getConfig", actionConfig);
        mockInvocation.expectAndReturn("getProxy", ((ActionProxy) actionProxy.proxy()));

        invocation = (ActionInvocation) mockInvocation.proxy();
    }
