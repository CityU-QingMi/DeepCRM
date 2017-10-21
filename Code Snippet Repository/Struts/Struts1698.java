    @Override
    protected void setUp() throws Exception {
        super.setUp();
        validationInterceptor = new ValidationInterceptor();
        validationInterceptor.setIncludeMethods("*");

        ActionConfig config = new ActionConfig.Builder("", "name", "")
        	.addInterceptor(new InterceptorMapping("validationInterceptor", validationInterceptor))
        	.build();
        ActionInvocation invocation = EasyMock.createNiceMock(ActionInvocation.class);
        ActionProxy proxy = EasyMock.createNiceMock(ActionProxy.class);

        EasyMock.expect(invocation.getProxy()).andReturn(proxy).anyTimes();
        EasyMock.expect(invocation.getAction()).andReturn(null).anyTimes();
        EasyMock.expect(invocation.invoke()).andReturn(Action.SUCCESS).anyTimes();
        EasyMock.expect(proxy.getMethod()).andReturn("execute").anyTimes();
        EasyMock.expect(proxy.getConfig()).andReturn(config).anyTimes();
        
        EasyMock.replay(invocation);
        EasyMock.replay(proxy);

        Map<String, ActionConfig> defaultNamespace = configuration.getRuntimeConfiguration().getActionConfigs().get("");
        defaultNamespace.put("actionName", config);

        ((DefaultActionMapper) container.getInstance(ActionMapper.class)).setAllowDynamicMethodCalls("true");
        
        ActionContext.getContext().setActionInvocation(invocation);
    }
