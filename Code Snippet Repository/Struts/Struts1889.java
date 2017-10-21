    public void testCookieCreation() throws Exception {

        prepare(I18nInterceptor.DEFAULT_COOKIE_PARAMETER, "da_DK");

        final Cookie cookie = new Cookie(I18nInterceptor.DEFAULT_COOKIE_ATTRIBUTE, "da_DK");

        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        response.addCookie(CookieMatcher.eqCookie(cookie));
        EasyMock.replay(response);

        ac.put(StrutsStatics.HTTP_RESPONSE, response);
        interceptor.setLocaleStorage(I18nInterceptor.Storage.COOKIE.name());
        interceptor.intercept(mai);

        EasyMock.verify(response);

        assertNull(session.get(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE)); // should not be stored here
        assertNull(session.get(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE)); // should not create a locale object
    }
