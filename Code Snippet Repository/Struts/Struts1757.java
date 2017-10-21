    public void testPutOnSessionMapUsesWrappedSessionsSetsAttribute() throws Exception {
        Object value = new Object();
        sessionMock.expect("getAttribute", new Constraint[]{new IsAnything()});
        sessionMock.expect("setAttribute", new Constraint[]{
                new IsEqual("KEY"), new IsEqual(value)
        });

        SessionMap sessionMap = new SessionMap((HttpServletRequest) requestMock.proxy());
        sessionMap.put("KEY", value);
        sessionMock.verify();
    }
