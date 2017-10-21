    public void testThrownExceptionMatching() throws Exception {
        this.setUpWithExceptionMappings();

        Mock action = new Mock(Action.class);
        Exception exception = new XWorkException("test");
        mockInvocation.expectAndThrow("invoke", exception);
        mockInvocation.matchAndReturn("getAction", ((Action) action.proxy()));
        String result = interceptor.intercept(invocation);
        assertNotNull(stack.findValue("exception"));
        assertEquals(stack.findValue("exception"), exception);
        assertEquals(result, "spooky");
        ExceptionHolder holder = (ExceptionHolder) stack.getRoot().get(0); // is on top of the root
        assertNotNull(holder.getExceptionStack()); // to invoke the method for unit test
    }
