    public void testGetOnSessionMapUsesWrappedSessionsGetAttribute() throws Exception {
        Object value = new Object();
        sessionMock.expectAndReturn("getAttribute", new Constraint[]{
                new IsEqual("KEY")
        }, value);

        SessionMap sessionMap = new SessionMap((HttpServletRequest) requestMock.proxy());
        assertEquals("Expected the get using KEY to return the value object setup in the mockSession", value, sessionMap.get("KEY"));
        sessionMock.verify();
    }
