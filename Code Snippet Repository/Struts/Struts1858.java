    public void testCreateSession() throws Exception {
        Mock httpServletRequestMock = new Mock(HttpServletRequest.class);
        httpServletRequestMock.expects(new InvokeOnceMatcher()).method("getSession").with(new IsEqual(Boolean.FALSE));
        httpServletRequestMock.expects(new InvokeOnceMatcher()).method("getSession").with(new IsEqual(Boolean.TRUE));
        httpServletRequestMock.expects(new InvokeOnceMatcher()).method("getSession").with(new IsEqual(Boolean.FALSE));
        HttpServletRequest request = (HttpServletRequest) httpServletRequestMock.proxy();

        ServletActionContext.setRequest(request);

        CreateSessionInterceptor interceptor = new CreateSessionInterceptor();
        interceptor.intercept(new MockActionInvocation());

        httpServletRequestMock.verify();
    }
