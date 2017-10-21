    public void testCookieCreation() throws Exception {
        // given
        CookieProviderInterceptor interceptor = new CookieProviderInterceptor();

        final Cookie cookie = new Cookie("foo", "bar");

        CookieProvider action = new CookieProvider() {
            public Set<Cookie> getCookies() {
                Set<Cookie> cookies = new HashSet<Cookie>();
                cookies.add(cookie);
                return cookies;
            }
        };

        HttpServletResponse response = EasyMock.createNiceMock(HttpServletResponse.class);
        response.addCookie(cookie);
        EasyMock.replay(response);

        // when
        interceptor.addCookiesToResponse(action, response);

        // then
        EasyMock.verify(response);
    }
