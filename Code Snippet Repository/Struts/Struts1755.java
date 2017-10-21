    public void testRemovePassThroughCallToRemoveAttribute() throws Exception {
        Object value = new Object();
        sessionMock.expectAndReturn("getAttribute", new Constraint[]{
                new IsEqual("KEY")
        }, value);
        sessionMock.expect("removeAttribute", new Constraint[]{
                new IsEqual("KEY")
        });

        SessionMap sessionMap = new SessionMap((HttpServletRequest) requestMock.proxy());
        assertEquals(value, sessionMap.remove("KEY"));
        sessionMock.verify();
    }
