    public void testIncludeParameterInResult() throws Exception {

        ResultConfig resultConfig = new ResultConfig.Builder("", "")
            .addParam("namespace", "someNamespace")
            .addParam("encode", "true")
            .addParam("parse", "true")
            .addParam("location", "someLocation")
            .addParam("prependServletContext", "true")
            .addParam("method", "someMethod")
            .addParam("statusCode", "333")
            .addParam("param1", "value 1")
            .addParam("param2", "value 2")
            .addParam("param3", "value 3")
            .build();

        ActionContext context = ActionContext.getContext();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        context.put(ServletActionContext.HTTP_REQUEST, req);
        context.put(ServletActionContext.HTTP_RESPONSE, res);


        Map<String, ResultConfig> results=  new HashMap<String, ResultConfig>();
        results.put("myResult", resultConfig);

        ActionConfig actionConfig = new ActionConfig.Builder("", "", "")
                .addResultConfigs(results).build();

        ServletRedirectResult result = new ServletRedirectResult();
        result.setLocation("/myNamespace/myAction.action");
        result.setParse(false);
        result.setEncode(false);
        result.setPrependServletContext(false);
        result.setAnchor("fragment");
        result.setUrlHelper(new DefaultUrlHelper());

        IMocksControl control = createControl();
        ActionProxy mockActionProxy = control.createMock(ActionProxy.class);
        ActionInvocation mockInvocation = control.createMock(ActionInvocation.class);
        expect(mockInvocation.getProxy()).andReturn(mockActionProxy);
        expect(mockInvocation.getResultCode()).andReturn("myResult");
        expect(mockActionProxy.getConfig()).andReturn(actionConfig);
        expect(mockInvocation.getInvocationContext()).andReturn(context);

        control.replay();
        result.setActionMapper(container.getInstance(ActionMapper.class));
        result.execute(mockInvocation);
        assertEquals("/myNamespace/myAction.action?param1=value+1&param2=value+2&param3=value+3#fragment", res.getRedirectedUrl());
        control.verify();
    }
