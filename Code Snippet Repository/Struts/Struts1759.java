    public void testPutObjectOnSessionMapUsesWrappedSessionsSetsAttributeWithStringValue() throws Exception {
        Object key = new Object();
        Object value = new Object();
        sessionMock.expect("getAttribute", new Constraint[]{new IsAnything()});
        sessionMock.expect("setAttribute", new Constraint[]{
                new IsEqual(key.toString()), new IsEqual(value)
        });

        SessionMap sessionMap = new SessionMap((HttpServletRequest) requestMock.proxy());
        sessionMap.put(key, value);
        sessionMock.verify();
    }
