    public void testPuttingObjectInMapReturnsPreviousValueForKey() throws Exception {
        Object originalValue = new Object();
        Object value = new Object();
        sessionMock.expectAndReturn("getAttribute", new Constraint[]{
                new IsEqual("KEY")
        }, null);
        sessionMock.expect("setAttribute", new Constraint[]{
                new IsEqual("KEY"), new IsEqual(originalValue)
        });
        sessionMock.expectAndReturn("getAttribute", new Constraint[]{
                new IsEqual("KEY")
        }, originalValue);
        sessionMock.expect("setAttribute", new Constraint[]{
                new IsEqual("KEY"), new IsEqual(value)
        });

        SessionMap sessionMap = new SessionMap((HttpServletRequest) requestMock.proxy());
        sessionMap.put("KEY", originalValue);
        assertEquals("should be the OriginalValue, as the contract for Map says that put returns the previous value in the map for the key", originalValue, sessionMap.put("KEY", value));
        sessionMock.verify();
    }
