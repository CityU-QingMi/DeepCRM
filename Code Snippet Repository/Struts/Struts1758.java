    public void testGetObjectOnSessionMapUsesWrappedSessionsGetAttributeWithStringValue() throws Exception {
        Object key = new Object();
        Object value = new Object();
        sessionMock.expectAndReturn("getAttribute", new Constraint[]{
                new IsEqual(key.toString())
        }, value);

        SessionMap sessionMap = new SessionMap((HttpServletRequest) requestMock.proxy());
        assertEquals("Expected the get using KEY to return the value object setup in the mockSession", value, sessionMap.get(key));
        sessionMock.verify();
    }
