    public void testSimple() {
        ServletDispatcherResult view = new ServletDispatcherResult();
        view.setLocation("foo.jsp");

        Mock dispatcherMock = new Mock(RequestDispatcher.class);
        dispatcherMock.expect("forward", C.ANY_ARGS);

        Mock requestMock = new Mock(HttpServletRequest.class);
        requestMock.expectAndReturn("getAttribute", "struts.actiontag.invocation", null);
        requestMock.expectAndReturn("getAttribute", "javax.servlet.include.servlet_path", null);
        requestMock.expectAndReturn("getRequestDispatcher", C.args(C.eq("foo.jsp")), dispatcherMock.proxy());
        requestMock.expect("setAttribute", C.ANY_ARGS); // this is a bad mock, but it works
        requestMock.expect("setAttribute", C.ANY_ARGS); // this is a bad mock, but it works
        requestMock.matchAndReturn("getRequestURI", "foo.jsp");

        Mock responseMock = new Mock(HttpServletResponse.class);
        responseMock.expectAndReturn("isCommitted", Boolean.FALSE);

        ActionContext ac = new ActionContext(Ognl.createDefaultContext(null));
        ActionContext.setContext(ac);
        ServletActionContext.setRequest((HttpServletRequest) requestMock.proxy());
        ServletActionContext.setResponse((HttpServletResponse) responseMock.proxy());

        try {
            view.execute(null);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        dispatcherMock.verify();
        requestMock.verify();
        dispatcherMock.verify();
    }
