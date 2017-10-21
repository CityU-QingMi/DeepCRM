    protected void setUp() throws Exception {
        super.setUp();
        configurationManager.getConfiguration().
            addPackageConfig("foo", new PackageConfig.Builder("foo").namespace("/namespace").build());

        view = new ServletRedirectResult();
        container.inject(view);

        responseMock = new Mock(HttpServletResponse.class);

        requestMock = new Mock(HttpServletRequest.class);
        requestMock.matchAndReturn("getContextPath", "/context");

         ResultConfig resultConfig = new ResultConfig.Builder("", "").build();

        Map<String, ResultConfig> results=  new HashMap<String, ResultConfig>();
        results.put("myResult", resultConfig);

        ActionConfig actionConfig = new ActionConfig.Builder("", "", "")
                .addResultConfigs(results).build();

        ActionContext ac = new ActionContext(Ognl.createDefaultContext(null));
        ac.put(ServletActionContext.HTTP_REQUEST, requestMock.proxy());
        ac.put(ServletActionContext.HTTP_RESPONSE, responseMock.proxy());
        MockActionInvocation ai = new MockActionInvocation();
        ai.setInvocationContext(ac);
        ai.setResultCode("myResult");
        ActionProxy mockActionProxy = createNiceMock(ActionProxy.class);
        ai.setProxy(mockActionProxy);
        expect(mockActionProxy.getConfig()).andReturn(actionConfig).anyTimes();
        replay(mockActionProxy);
        this.ai = ai;
        ai.setStack(ActionContext.getContext().getValueStack());
    }
