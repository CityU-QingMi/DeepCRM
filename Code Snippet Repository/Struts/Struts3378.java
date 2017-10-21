    protected void setUp() throws Exception {
        super.setUp();
        ActionConfig config = new ActionConfig.Builder("", "name", "").build();
        this.action = new TestAction();
        this.interceptor = new JSONValidationInterceptor();
        this.validationInterceptor = new AnnotationValidationInterceptor();
        container.inject(validationInterceptor);
        this.request = new StrutsMockHttpServletRequest();
        stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        this.response = new StrutsMockHttpServletResponse();
        response.setWriter(writer);

        ActionContext context = ActionContext.getContext();

        context.put(StrutsStatics.HTTP_REQUEST, request);
        context.put(StrutsStatics.HTTP_RESPONSE, response);

        StrutsMockServletContext servletContext = new StrutsMockServletContext();

        context.put(StrutsStatics.SERVLET_CONTEXT, servletContext);
        invocation = new MockActionInvocation();
        ActionContext.getContext().setActionInvocation(invocation);
        invocation.setAction(action);
        invocation.setInvocationContext(context);
        MockActionProxy proxy = new MockActionProxy();
        proxy.setMethod("execute");
        proxy.setAction(action);
        proxy.setConfig(config);
        invocation.setProxy(proxy);
    }
