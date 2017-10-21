    public void testWithPreResultListenerAgainstMaliciousCode() throws Exception {
        conversionErrors.put("foo", "\" + #root + \"");

        ActionContext ac = createActionContext();

        MockActionInvocation mai = createActionInvocation(ac);

        SimpleAction action = createAction(mai);
        assertNull(action.getFieldErrors().get("foo"));
        assertEquals(55, stack.findValue("foo"));

        interceptor.doIntercept(mai);

        assertTrue(action.hasFieldErrors());
        assertNotNull(action.getFieldErrors().get("foo"));

        assertEquals("\" + #root + \"", stack.findValue("foo"));
    }
