    public void testCookiesWithStrutsInternalsAccess() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        String sessionCookieName = "session.userId";
        String sessionCookieValue = "session.userId=1";
        String appCookieName = "application.userId";
        String appCookieValue = "application.userId=1";
        String reqCookieName = "request.userId";
        String reqCookieValue = "request.userId=1";

        request.setCookies(
                new Cookie(sessionCookieName, "1"),
                new Cookie("1", sessionCookieValue),
                new Cookie(appCookieName, "1"),
                new Cookie("1", appCookieValue),
                new Cookie(reqCookieName, "1"),
                new Cookie("1", reqCookieValue)
            );
        ServletActionContext.setRequest(request);

        final Map<String, Boolean> excludedName = new HashMap<String, Boolean>();

        CookieInterceptor interceptor = new CookieInterceptor() {
            @Override
            protected boolean isAcceptableName(String name) {
                boolean accepted = super.isAcceptableName(name);
                excludedName.put(name, accepted);
                return accepted;
            }
        };
        interceptor.setExcludedPatternsChecker(new DefaultExcludedPatternsChecker());
        interceptor.setAcceptedPatternsChecker(new DefaultAcceptedPatternsChecker());
        interceptor.setCookiesName("*");

        MockActionInvocation invocation = new MockActionInvocation();
        invocation.setAction(new MockActionWithCookieAware());

        interceptor.intercept(invocation);

        assertFalse(excludedName.get(sessionCookieName));
        assertFalse(excludedName.get(appCookieName));
        assertFalse(excludedName.get(reqCookieName));
    }
