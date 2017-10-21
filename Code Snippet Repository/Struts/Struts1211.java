    public void testWithPreResultListener() throws Exception {
        conversionErrors.put("foo", "Hello");

        ActionContext ac = createActionContext();
        MockActionInvocation mai = createActionInvocation(ac);
        SimpleAction action = createAction(mai);

        assertNull(action.getFieldErrors().get("foo"));
        assertEquals(55, stack.findValue("foo"));

        interceptor.doIntercept(mai);

        assertTrue(action.hasFieldErrors());
        assertNotNull(action.getFieldErrors().get("foo"));

        assertEquals("Hello", stack.findValue("foo")); // assume that the original value is reset
    }
