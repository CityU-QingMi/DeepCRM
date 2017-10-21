    public void testFieldErrorAdded() throws Exception {
        conversionErrors.put("foo", 123L);

        ActionSupport action = new ActionSupport();
        mockInvocation.expectAndReturn("getAction", action);
        stack.push(action);
        mockInvocation.matchAndReturn("getAction",action);
        assertNull(action.getFieldErrors().get("foo"));
        interceptor.doIntercept(invocation);
        assertTrue(action.hasFieldErrors());
        assertNotNull(action.getFieldErrors().get("foo"));
    }
