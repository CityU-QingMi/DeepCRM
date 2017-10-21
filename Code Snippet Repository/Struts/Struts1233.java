    public void testThrownExceptionNoMatchLoggingCategoryLevelFatal() throws Exception {
        this.setupWithoutExceptionMappings();

        Mock action = new Mock(Action.class);
        Exception exception = new Exception("test");
        mockInvocation.expectAndThrow("invoke", exception);
        mockInvocation.matchAndReturn("getAction", ((Action) action.proxy()));

        try {
        	interceptor.setLogEnabled(true);
        	interceptor.setLogCategory("showcase.unhandled");
        	interceptor.setLogLevel("fatal");
            interceptor.intercept(invocation);
            fail("Should not have reached this point.");
        } catch (Exception e) {
            assertEquals(e, exception);
        }
        
        assertEquals("fatal", interceptor.getLogLevel());
        assertEquals(true, interceptor.isLogEnabled());
        assertEquals("showcase.unhandled", interceptor.getLogCategory());
    }
