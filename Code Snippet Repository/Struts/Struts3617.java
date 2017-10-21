    @Override
    public void setUp() {
        mgr = new DefaultContentTypeHandlerManager();
        mockResponse = new MockHttpServletResponse();
        mockRequest = new MockHttpServletRequest();
        mockRequest.setMethod("GET");
        ActionContext.setContext(new ActionContext(new HashMap()));
        ServletActionContext.setRequest(mockRequest);
        ServletActionContext.setResponse(mockResponse);

        invocation = new MockActionInvocation();
        invocation.setProxy(new MockActionProxy());
    }
