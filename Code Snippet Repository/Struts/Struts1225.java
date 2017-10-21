    public void testThrownExceptionNoMatchLoggingUnknownLevel() throws Exception {
        this.setupWithoutExceptionMappings();

        Mock action = new Mock(Action.class);
        Exception exception = new Exception("test");
        mockInvocation.expectAndThrow("invoke", exception);
        mockInvocation.matchAndReturn("getAction", ((Action) action.proxy()));

        try {
        	interceptor.setLogEnabled(true);
        	interceptor.setLogLevel("xxx");
            interceptor.intercept(invocation);
            fail("Should not have reached this point.");
        } catch (IllegalArgumentException e) {
        	// success
        }
    }
