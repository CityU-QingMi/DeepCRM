    public void testPuttingObjectInMapReturnsNullForPreviouslyUnusedKey() throws Exception {
        Object value = new Object();
        sessionMock.expectAndReturn("getAttribute", new Constraint[]{
                new IsEqual("KEY")
        }, null);
        sessionMock.expect("setAttribute", new Constraint[]{
                new IsEqual("KEY"), new IsEqual(value)
        });

        SessionMap sessionMap = new SessionMap((HttpServletRequest) requestMock.proxy());
        assertNull("should be null, as the contract for Map says that put returns the previous value in the map for the key", sessionMap.put("KEY", value));
        sessionMock.verify();
    }
