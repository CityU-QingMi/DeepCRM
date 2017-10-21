    public void testIntercepDefault() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(
                new Cookie("cookie1", "cookie1value"),
                new Cookie("cookie2", "cookie2value"),
                new Cookie("cookie3", "cookie3value")
        );
        ServletActionContext.setRequest(request);

        MockActionWithCookieAware action = new MockActionWithCookieAware();

        ActionContext.getContext().getValueStack().push(action);

        ActionInvocation invocation = (ActionInvocation) createMock(ActionInvocation.class);
        
        expect(invocation.getAction()).andReturn(action);
        expect(invocation.invoke()).andReturn(Action.SUCCESS);

        replay(invocation);

        // by default the interceptor doesn't accept any cookies
        CookieInterceptor interceptor = new CookieInterceptor();
        interceptor.setExcludedPatternsChecker(new DefaultExcludedPatternsChecker());
        interceptor.setAcceptedPatternsChecker(new DefaultAcceptedPatternsChecker());

        interceptor.intercept(invocation);

        assertTrue(action.getCookiesMap().isEmpty());
        assertNull(action.getCookie1(), null);
        assertNull(action.getCookie2(), null);
        assertNull(action.getCookie3(), null);
        assertNull(ActionContext.getContext().getValueStack().findValue("cookie1"));
        assertNull(ActionContext.getContext().getValueStack().findValue("cookie2"));
        assertNull(ActionContext.getContext().getValueStack().findValue("cookie3"));
        
        verify(invocation);
    }
