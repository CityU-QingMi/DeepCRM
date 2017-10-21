    public void testInterceptAll2() throws Exception {
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

        CookieInterceptor interceptor = new CookieInterceptor();
        interceptor.setExcludedPatternsChecker(new DefaultExcludedPatternsChecker());
        interceptor.setAcceptedPatternsChecker(new DefaultAcceptedPatternsChecker());
        interceptor.setCookiesName("cookie1, cookie2, cookie3");
        interceptor.setCookiesValue("cookie1value, cookie2value, cookie3value");
        interceptor.intercept(invocation);

        assertFalse(action.getCookiesMap().isEmpty());
        assertEquals(action.getCookiesMap().size(), 3);
        assertEquals(action.getCookiesMap().get("cookie1"), "cookie1value");
        assertEquals(action.getCookiesMap().get("cookie2"), "cookie2value");
        assertEquals(action.getCookiesMap().get("cookie3"), "cookie3value");
        assertEquals(action.getCookie1(), "cookie1value");
        assertEquals(action.getCookie2(), "cookie2value");
        assertEquals(action.getCookie3(), "cookie3value");
        assertEquals(ActionContext.getContext().getValueStack().findValue("cookie1"), "cookie1value");
        assertEquals(ActionContext.getContext().getValueStack().findValue("cookie2"), "cookie2value");
        assertEquals(ActionContext.getContext().getValueStack().findValue("cookie3"), "cookie3value");
        
        verify(invocation);
    }
