    public void testMultipleParametersRedirect() throws Exception {
        view.setLocation("foo.jsp?foo=bar&amp;baz=jim");
        requestMock.expectAndReturn("getParameterMap", new HashMap());
        requestMock.expectAndReturn("getServletPath", "/namespace/some.action");
        requestMock.expectAndReturn("getRequestURI", "/namespace/some.action");
        requestMock.expectAndReturn("getAttribute", C.ANY_ARGS, null);
        responseMock.expectAndReturn("encodeRedirectURL", "/context/namespace/foo.jsp?foo=bar&amp;baz=jim", "/context/namespace/foo.jsp?foo=bar&baz=jim");
        responseMock.expect("sendRedirect", C.args(C.eq("/context/namespace/foo.jsp?foo=bar&baz=jim")));

        view.execute(ai);

        requestMock.verify();
        responseMock.verify();
    }
