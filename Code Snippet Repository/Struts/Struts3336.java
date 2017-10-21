    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();

        ActionContext context = ActionContext.getContext();
        ValueStack stack = context.getValueStack();

        ActionContext.setContext(context);
        context.put(StrutsStatics.HTTP_REQUEST, this.request);
        context.put(StrutsStatics.HTTP_RESPONSE, this.response);

        MockServletContext servletContext = new MockServletContext();

        context.put(StrutsStatics.SERVLET_CONTEXT, servletContext);
        this.invocation = new MockActionInvocationEx();
        this.invocation.setInvocationContext(context);
        this.invocation.setStack(stack);
    }
