    public void testWithParameter() {
        ServletDispatcherResult view = container.inject(ServletDispatcherResult.class);
        view.setLocation("foo.jsp?bar=1");

        Mock dispatcherMock = new Mock(RequestDispatcher.class);
        dispatcherMock.expect("forward", C.ANY_ARGS);

        Mock requestMock = new Mock(HttpServletRequest.class);
        requestMock.expectAndReturn("getAttribute", "struts.actiontag.invocation", null);
        requestMock.expectAndReturn("getAttribute", "javax.servlet.include.servlet_path", null);
        requestMock.expectAndReturn("getRequestDispatcher", C.args(C.eq("foo.jsp?bar=1")), dispatcherMock.proxy());
        requestMock.expect("setAttribute", C.ANY_ARGS); // this is a bad mock, but it works
        requestMock.expect("setAttribute", C.ANY_ARGS); // this is a bad mock, but it works
        requestMock.matchAndReturn("getRequestURI", "foo.jsp");

        Mock responseMock = new Mock(HttpServletResponse.class);
        responseMock.expectAndReturn("isCommitted", Boolean.FALSE);

        ActionContext ac = new ActionContext(Ognl.createDefaultContext(null));
        ac.setContainer(container);
        ActionContext.setContext(ac);
        ServletActionContext.setRequest((HttpServletRequest) requestMock.proxy());
        ServletActionContext.setResponse((HttpServletResponse) responseMock.proxy());

        MockActionInvocation mockActionInvocation = new MockActionInvocation();
        mockActionInvocation.setInvocationContext(ac);
        mockActionInvocation.setStack(container.getInstance(ValueStackFactory.class).createValueStack());

        try {
            view.execute(mockActionInvocation);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(mockActionInvocation.getInvocationContext().getParameters().contains("bar"));
        assertEquals("1", mockActionInvocation.getInvocationContext().getParameters().get("bar").getValue());
        assertEquals("1", ((HttpParameters) mockActionInvocation.getInvocationContext().getContextMap().get("parameters")).get("bar").getValue());
        dispatcherMock.verify();
        requestMock.verify();
        dispatcherMock.verify();
    }
